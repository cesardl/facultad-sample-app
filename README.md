# Facultad Sample Application
[![Build Status](https://travis-ci.org/cesardl/facultad-sample-app.svg?branch=master)](https://travis-ci.org/cesardl/facultad-sample-app) [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sanmarcux.samples.mysql.facultad&metric=alert_status)](https://sonarcloud.io/project/issues?id=org.sanmarcux.samples.mysql.facultad&resolved=false) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.sanmarcux.samples.mysql.facultad&metric=coverage)](https://sonarcloud.io/component_measures?id=org.sanmarcux.samples.mysql.facultad&metric=coverage) [![Maintainability](https://api.codeclimate.com/v1/badges/07262732998e04a64027/maintainability)](https://codeclimate.com/github/cesardl/facultad-sample-app/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/07262732998e04a64027/test_coverage)](https://codeclimate.com/github/cesardl/facultad-sample-app/test_coverage)

El siguiente es una aplicacion en el cual se ve el uso de las distintas query SQL para el SGBD MySQL.

Para activar el idioma en ingl&eacute;s `-Duser.language=en -Duser.country=US -Duser.variant=US`

## v 2.0.0
Ya con el pasar de los años me animé a hacer la actualización a Java 8 y utilizar Spring Framework. Además estoy considerando la creación de test unitarios que en su momento no hice.

## v 1.0.0
Esta aplicacion la hice cuando estaba en 3er año en la UNMSM, la estoy actualizando para que tenga funcionalidades de Java 7, otras librerias graficas y la implementacion de algunos patrones de diseño de software.

## Troubleshooting

Al realizar la configuraci&oacute;n en travis tuve un problemilla ya que la clase `javax.swing.JDialog` necesita la variable X11 configurada para poder levantar su dependencia gr&aacute;fica.

Aqu&iacute; explican como simular un entorno gr&aacute;fico en Travis.

```
https://docs.travis-ci.com/user/gui-and-headless-browsers/#Using-xvfb-directly
```

### For usage

#### Creating schema

```mysql
CREATE DATABASE IF NOT EXISTS facultad CHARACTER SET utf8 COLLATE utf8_general_ci
```

#### Schema backup

```bash
mysqldump -u root -p facultad -r src/main/resources/db/facultad_schema_and_data.sql
```
