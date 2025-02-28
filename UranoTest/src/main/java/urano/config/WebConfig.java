package urano.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

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
        String profileName = JsonConfigReader.getChromeProfilePath();
        options.addArguments("user-data-dir=" + profilePath);
        options.addArguments("profile-directory" + profileName);

        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");

        if (JsonConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile fxProfile = profile.getProfile("testautomation-profile");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(fxProfile);

        /*Percorso del profilo utente dal file JSON
        String profilePath = JsonConfigReader.getFirefoxProfilePath();
        if (profilePath != null && !profilePath.isEmpty() && new File(profilePath).exists()) {
            System.out.println("Profilo caricato: " + profilePath);
            options.addArguments("-profile", profilePath);
        } else {
            System.out.println("Percorso profilo Firefox non valido o non trovato!");
        }
         */
        // Avvio con finestra massimizzata
        options.addArguments("--start-maximized");

        // Disabilita GPU
        options.addArguments("--disable-gpu");

        // Modalità headless se configurato nel JSON
        if (JsonConfigReader.isHeadless()) {
            options.addArguments("--headless");
        }

        // Disabilita notifiche
        options.addPreference("dom.webnotifications.enabled", false);

        // Ignora certificati SSL
        options.addPreference("network.stricttransportsecurity.preloadlist", false);
        options.addPreference("security.insecure_field_warning.contextual.enabled", false);

        WebDriver driver = new FirefoxDriver(options);
        System.out.println(driver.manage().getCookies());
        return driver;
    }
}