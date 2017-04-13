package org.sanmarcux.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sanmarcux.init.AppConfig;
import org.sanmarcux.init.DatabaseTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DatabaseTestConfig.class})
public class JFrameInitTest {

    @Autowired
    private JFrameInit frame;

    @Test
    public void testShowMainFrame() {
        assertNotNull(frame);
    }

}