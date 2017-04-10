package org.sanmarcux.view;

import org.junit.Test;

import static org.junit.Assert.*;

public class JFrameInitTest {

    @Test
    public void testShowMainFrame() {
        JFrameInit frame = new JFrameInit();

        assertNotNull(frame);
    }

}