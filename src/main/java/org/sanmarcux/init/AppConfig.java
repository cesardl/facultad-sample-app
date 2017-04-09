package org.sanmarcux.init;

import org.sanmarcux.view.JFrameInit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created on 26/02/2017.
 *
 * @author Cesardl
 */
@Configuration
@ComponentScan(basePackages = "org.sanmarcux")
@PropertySource("classpath:db/database.properties")
public class AppConfig {

    @Value("${database.driver}")
    private String driver;

    @Value("${database.url}")
    private String url;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    @Bean
    public JFrameInit createMainFrame() {
        return new JFrameInit();
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
