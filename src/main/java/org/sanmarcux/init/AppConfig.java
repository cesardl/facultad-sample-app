package org.sanmarcux.init;

import org.sanmarcux.view.JFrameInit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created on 26/02/2017.
 *
 * @author Cesardl
 */
@Configuration
@ComponentScan(basePackages = "org.sanmarcux")
public class AppConfig {

    @Bean
    public JFrameInit createMainFrame() {
        return new JFrameInit();
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        return dataSource;
    }
}
