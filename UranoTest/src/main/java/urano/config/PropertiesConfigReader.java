package urano.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfigReader {
    private static final String CONFIG_PATH = "src/test/resources/config.properties";
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream(CONFIG_PATH);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file Properties", e);
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public static boolean isMaximized() {
        return Boolean.parseBoolean(properties.getProperty("maximize"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicitWait"));
    }
}
