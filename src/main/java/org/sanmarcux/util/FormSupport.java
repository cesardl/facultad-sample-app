package org.sanmarcux.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 13/04/2017.
 *
 * @author Cesardl
 */
@Component
public class FormSupport {

    public static final int BAD_ROW = -1;

    private Pattern emailPattern;

    @PostConstruct
    private void init() {
        //Set the email pattern string
        emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
    }

    /**
     * Generate randon number with length 8.
     *
     * @return generated value
     */
    public String generateRandomCode() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 100000000));
    }

    /**
     * Validate if email have correct pattern.
     *
     * @param email string to validate
     * @return true if string have a correct value
     */
    public boolean isCorrectEmailFormat(final String email) {
        //Match the given string with the pattern
        Matcher m = emailPattern.matcher(email);

        //Check whether match is found
        return m.matches();
    }
}
