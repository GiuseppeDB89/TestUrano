package urano.POM.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

public class HomePage {
    private WebDriver driver;

    private static final By TOP_NAVBAR = By.id("Z4F2-ZaiU0");
    private static final By SIDE_NAVBAR = By.id("EITgbgV2CE");
    private static final By TITLE = By.cssSelector(".rz-p-4 h1");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle() {
        return WebUtilities.findElement(driver, TITLE);
    }

}
