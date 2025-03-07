package urano.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonConfigReader {
    private static final String CONFIG_PATH = "src/test/resources/config.json";
    private static JsonNode config;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            config = objectMapper.readTree(new File(CONFIG_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file JSON", e);
        }
    }

    public static String getBrowser() {
        return config.get("browser").asText();
    }

    public static String getAesysEmail() {
        return config.get("aesysEmail").asText();
    }

    public static String getAesysPassword() {
        return config.get("aesysPassword").asText();
    }

    public static String getChromeProfilePath() {
        String profilePath = config.get("chromeProfilePath").asText();
        return profilePath.replace("%USER_HOME%", System.getProperty("user.home"));
    }

    public static String getChromeProfileName() {
        return config.get("chromeProfileName").asText();
    }

    public static String getFirefoxProfileName() {
        return config.get("firefoxProfileName").asText();
    }

    public static String getFirefoxProfilePath() {
        String profilePath = config.get("firefoxProfilePath").asText();
        return profilePath.replace("%USER_HOME%", System.getProperty("user.home"));
    }

    public static boolean isHeadless() {
        return config.get("headless").asBoolean();
    }

    public static int getImplicitWait() {
        return config.has("implicitWait") ? config.get("implicitWait").asInt() : 5;
    }
}
