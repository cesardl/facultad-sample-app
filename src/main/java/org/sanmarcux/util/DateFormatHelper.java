package org.sanmarcux.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cesardiaz
 */
@Component
public class DateFormatHelper {

    private static final String PATTERN = "dd/MMM/y";

    private java.text.SimpleDateFormat simpleDateFormat;

    @PostConstruct
    private void setUp() {
        simpleDateFormat = new java.text.SimpleDateFormat(PATTERN);
    }

    /**
     * Format the date.
     *
     * @param date the date to be formatted.
     * @return the date with format.
     */
    public String format(java.util.Date date) {
        return simpleDateFormat.format(date);
    }

}
