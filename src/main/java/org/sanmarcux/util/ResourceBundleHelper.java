package org.sanmarcux.util;

import org.springframework.stereotype.Component;

/**
 * @author cesardiaz
 */
@Component
public class ResourceBundleHelper {

    private final java.util.ResourceBundle bundle;

    public ResourceBundleHelper() {
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
