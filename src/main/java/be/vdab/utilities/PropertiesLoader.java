package be.vdab.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static PropertiesLoader ourInstance = new PropertiesLoader();

    private Properties properties;

    public static PropertiesLoader getInstance() {
        return ourInstance;
    }

    private PropertiesLoader() {
        if (null == properties || properties.isEmpty()) {
            loadProperties();
        }
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Boolean isCorporateProxyEnabled() { return Boolean.parseBoolean(properties.getProperty("corporate.proxy.enabled")); }

    public String getCorporateProxyHost() {
        return properties.getProperty("corporate.proxy.host");
    }

    public int getCorporateProxyPort() {
        return Integer.parseInt(properties.getProperty("corporate.proxy.port"));
    }

    public String[] getBlacklistedExtensions() {
        return properties.getProperty("blacklisted.extensions").split(",");
    }

}
