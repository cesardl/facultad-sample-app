package org.sanmarcux.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.sanmarcux",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.sanmarcux\\.init\\..*"))
public class DatabaseTestConfig {

    @Bean
    public DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScripts("data/script_student.sql", "data/script_teacher.sql")
                .build();
    }
}
