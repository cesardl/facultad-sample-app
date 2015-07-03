package util;

/**
 *
 * @author cesardiaz
 */
public class ResourceBundleHelper {

    private static ResourceBundleHelper instance = null;

    private final java.util.ResourceBundle bundle;

    private ResourceBundleHelper() {
        bundle = java.util.ResourceBundle.getBundle("view/Bundle");
    }

    public static synchronized ResourceBundleHelper getInstance() {
        if (instance == null) {
            instance = new ResourceBundleHelper();
        }

        return instance;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
    
}
