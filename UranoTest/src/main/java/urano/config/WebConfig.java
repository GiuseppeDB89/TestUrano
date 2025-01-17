package urano.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebConfig {
    public static WebDriver createChromeDriver() {
        String userHome = System.getProperty("user.home");
        String profilePath = userHome + "\\AppData\\Local\\Google\\Chrome\\User Data\\NewProfile";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + profilePath);
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*"); // Evita errori legati a CORS o altri blocchi
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");

        return new ChromeDriver(options);
    }
}
