package org.sanmarcux.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author cesardiaz
 */
@Component
public class ResourceBundleHelper {

    private java.util.ResourceBundle bundle;

    @PostConstruct
    private void init() {
        bundle = java.util.ResourceBundle.getBundle("view/Bundle");
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
    
}
