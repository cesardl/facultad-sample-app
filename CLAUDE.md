# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Swing desktop CRUD app (students / teachers) over MySQL, wired with Spring 4 (annotation config, `JdbcTemplate`, no Spring Boot). Single Maven module, Java 8 source/target, JUnit 4 + Mockito 1.x + H2 for tests. The README is in Spanish.

## Commands

```bash
mvn clean verify                          # build + all tests + jacoco report + assembly (what CI runs)
mvn test                                  # tests only
mvn test -Dtest=StudentDAOImplTest        # one class
mvn test -Dtest=StudentDAOImplTest#testInsert   # one method
```

- **Requires JDK 8.** The code uses `javax.annotation.PostConstruct`, which was removed from the JDK in 11; on JDK 11+ compilation fails with `cannot find symbol ... PostConstruct`. (This machine's default is JDK 11 — point `JAVA_HOME` at a JDK 8 first.)
- The view tests instantiate real Swing components, so they need a display. CI runs `xvfb-run mvn clean verify` (see `.travis.yml`); on a headless Linux box do the same.
- `mvn package` also produces `target/facultad-distribution.{zip,tar.gz}` via `src/assembly/distribution.xml`.

## Running the app

- Needs a MySQL `facultad` database: load `db/facultad_schema_and_data.sql` (and `db/facultad_db_user.sql` for the user).
- `database.password` in `src/main/resources/config/database.properties` is a Jasypt `ENC(...)` value. It is decrypted using the env var **`APP_ENCRYPTION_PASSWORD`** (algorithm `PBEWithMD5AndDES`); without it the context fails to start. The value used by the launch scripts is in `src/main/scripts/startup.*`.
- Entry point is `org.sanmarcux.Main`. UI language follows the JVM locale (`-Duser.language=en -Duser.country=US -Duser.variant=US` for English; default bundle is Spanish).
- Config location differs by mode: without `-Dapp.home` it reads `config/database.properties` from the classpath (IDE run); with `-Dapp.home=<dir>` it reads `<dir>/config/database.properties` from disk (distribution run). The jar plugin **excludes `**/config/**`**, so the packaged jar never contains the properties — the assembly ships them next to it instead.

## Architecture

Package root `org.sanmarcux`; everything is discovered by a single `@ComponentScan("org.sanmarcux")` in `init/DatabaseConfig`, which also defines the `DataSource` and the Jasypt-aware placeholder configurer.

Layers, one vertical slice per entity (`Student`, `Teacher`):

`view/JPanelX` + `view/JDialogX` → `controller/XController` (+`impl`) → `dao/XDAO` (+`impl`) → MySQL

- Generic contracts `Controller<T>` and `DAO<T>` define the shared CRUD surface (lookup is by business **code**, not id; `saveOrUpdate` inserts when `id == 0`).
- Views are Spring beans (`@Component`) too, and use field/constructor injection. The Swing layout code sits in NetBeans-generated `initComponents()` blocks (`//GEN-BEGIN` markers, run via `@PostConstruct`) — don't hand-reformat those.
- Shared view behavior lives in `view/etc`: `JPanelBase<T>` (table double-click → edit dialog, Delete key → delete, template methods `addRow`/`setRowValues`/`deleteRow`/`showDialog`), `JDialogFormBase<T>` (validate/set/get entity), `Toast` for user feedback. Adding an entity means adding a new slice following this pattern and wiring a panel into `JFrameInit`'s tabbed pane.
- Panels do not reload from the DB after a save; they mutate their `DefaultTableModel` directly from the entity returned by the dialog.
- All user-facing strings go through `ResourceBundleHelper` → `view/Bundle*.properties`.
- DB naming is Spanish (`alumno`, `profesor`, columns like `cod_alum`, `id_prof`); Java naming is English. `Student.teacherId` maps to `profesor_id_prof` (FK), and `JDialogStudent` fills its teacher combo from `TeacherController.getNames()`.

## Testing setup

- DAO tests (`dao/impl/*Test`) build their own embedded **H2** DB from `src/test/resources/data/script_{student,teacher}.sql`. Those scripts are an H2-compatible copy of the MySQL schema (e.g. `sexo_alum` is `varchar(6)` instead of an `enum`) — keep them in sync with `db/` by hand.
- Controller tests use `@InjectMocks` + Mockito with the DAO mocked.
- View tests use `SpringJUnit4ClassRunner` + `init/DatabaseTestConfig` (same component scan, but excludes `org.sanmarcux.init.*` and substitutes the H2 `DataSource`), then swap collaborators with `@InjectMocks`/`@Mock`.
- Shared fixtures are in `src/test/java/org/sanmarcux/PojoFake.java`.
- Sonar (`sonar-project.properties`) excludes `Main` and `init/*` from analysis; CI only runs it on `master`.
