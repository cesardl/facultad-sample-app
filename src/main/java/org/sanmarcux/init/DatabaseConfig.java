package org.sanmarcux.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.nio.file.Paths;

/**
 * Created on 13/04/2017.
 *
 * @author Cesardl
 */
@Configuration
public class DatabaseConfig {

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.url}")
    private String databaseURL;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String appHome = System.getProperty("app.home");

        Resource location;
        if (appHome == null) {
            location = new ClassPathResource("config/database.properties");
        } else {
            location = new FileSystemResource(Paths.get(appHome, "config", "database.properties").toFile());
        }

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(location);
        return configurer;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseURL);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }
}
