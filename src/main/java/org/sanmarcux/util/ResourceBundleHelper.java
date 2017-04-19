package org.sanmarcux.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cesardiaz
 */
@Component
public class ResourceBundleHelper {

    private java.util.ResourceBundle bundle;

    @PostConstruct
    private void init() {
        bundle = java.util.ResourceBundle.getBundle("view/Bundle");
    }

    /**
     * Return a description defined on properties file.
     *
     * @param key key to search
     * @return the value
     */
    public String getString(String key) {
        return bundle.getString(key);
    }

}
