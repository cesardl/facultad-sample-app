package org.sanmarcux.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;
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

    private SecureRandom secureRandom;
    private Pattern emailPattern;

    @PostConstruct
    private void init() {
        secureRandom = new SecureRandom();

        //Set the email pattern string
        emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
    }

    public String generateRandomCode() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 99999999 + 1));
    }

    public boolean isCorrectEmailFormat(final String email) {
        //Match the given string with the pattern
        Matcher m = emailPattern.matcher(email);

        //Check whether match is found
        return m.matches();
    }
}
