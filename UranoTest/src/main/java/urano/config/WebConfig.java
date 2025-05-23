package urano.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.time.Duration;

public class WebConfig {
    public static WebDriver createDriver() {
        String browser = JsonConfigReader.getBrowser();
        WebDriver driver = switch (browser.toLowerCase()) {
            case "chrome" -> createChromeDriver();
            case "firefox" -> createFirefoxDriver();
            default -> throw new IllegalArgumentException("Browser non supportato: " + browser);
        };

        int implicitWaitTime = JsonConfigReader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));

        return driver;
    }

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Usa il profilo configurato nel JSON
        String profilePath = JsonConfigReader.getChromeProfilePath();
        String profileName = JsonConfigReader.getChromeProfileName();
        options.addArguments("user-data-dir=" + profilePath);
        options.addArguments("profile-directory=" + profileName);

        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");

        if (JsonConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        String profilePath = JsonConfigReader.getFirefoxProfilePath();
        File profileDir = new File(profilePath);

        if (!profileDir.exists() || !profileDir.isDirectory()) {
            throw new RuntimeException("Il profilo Firefox non esiste: " + profilePath);
        }

        // Configura il profilo di Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-profile", profilePath);
        options.addArguments("--disable-gpu", "--disable-dev-shm-usage", "--no-sandbox", "--window-size=1920,1080");

        if (JsonConfigReader.isHeadless()) {
            options.addArguments("--headless");
        }

        // Preferenze avanzate direttamente su FirefoxOptions
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3))
                .pageLoadTimeout(Duration.ofSeconds(10));

        return driver;
    }
}