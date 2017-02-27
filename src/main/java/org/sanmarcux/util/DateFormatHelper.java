package org.sanmarcux.util;

/**
 *
 * @author cesardiaz
 */
public class DateFormatHelper {

    private static final String PATTERN = "dd/MMM/y";
    
    private static DateFormatHelper instance = null;

    private final java.text.SimpleDateFormat simpleDateFormat;

    private DateFormatHelper() {
        simpleDateFormat = new java.text.SimpleDateFormat(PATTERN);
    }

    public static synchronized DateFormatHelper getInstance() {
        if (instance == null) {
            instance = new DateFormatHelper();
        }

        return instance;
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
