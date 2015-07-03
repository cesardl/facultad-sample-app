package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cesardiaz
 */
public class DateFormatHelper {

    private static final String PATTERN = "dd/MMM/y";
    
    private static DateFormatHelper instance = null;

    private final SimpleDateFormat simpleDateFormat;

    private DateFormatHelper() {
        simpleDateFormat = new SimpleDateFormat(PATTERN);
    }

    public static synchronized DateFormatHelper getInstance() {
        if (instance == null) {
            instance = new DateFormatHelper();
        }

        return instance;
    }
    
    /**
     *
     * @param date
     * @return
     */
    public String format(Date date) {
        return simpleDateFormat.format(date);
    }
    
}
