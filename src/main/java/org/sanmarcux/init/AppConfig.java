package org.sanmarcux.init;

import org.sanmarcux.view.JFrameInit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}
