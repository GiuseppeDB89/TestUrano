package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class RiepilogoTimbraturePage {
    private WebDriver driver;

    private static final By MONTHS_YEARS_DROPDOWN = By.className("rz-splitbutton-menubutton");
    private static final By MONTHS_YEARS_OPTIONS = By.className("rz-menuitem");
    private static final By CERCA_BUTTON = By.className("rz-button-text");
    private static final By GO_BACK_BUTTON = By.cssSelector("[type='button']");
    private static final By GRID = By.className("rz-data-grid");

    public RiepilogoTimbraturePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTimbratureGrid() {
        return WebUtilities.findElement(driver, GRID);
    }

    public void selectMonthsDropdownOption(String month) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, MONTHS_YEARS_DROPDOWN);
        dropdowns.getFirst().click();
        List<WebElement> options = WebUtilities.findElements(driver, MONTHS_YEARS_OPTIONS);
        for(WebElement option : options) {
            if(option.getText().contains(month)) {
                option.click();
            }
        }
    }

    public void selectYearsDropdownOption(String year) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, MONTHS_YEARS_DROPDOWN);
        dropdowns.getLast().click();
        List<WebElement> options = WebUtilities.findElements(driver, MONTHS_YEARS_OPTIONS);
        for (WebElement option : options) {
            if (option.getText().contains(year)) {
                option.click();
            }
        }
    }

    public void clickCercaButton() {
        List<WebElement> buttons = WebUtilities.findElements(driver, CERCA_BUTTON);
        for (WebElement button : buttons) {
            if (button.getText().contains("Cerca")) {
                button.click();
                return;
            }
        }
    }

    public void clickGoBackButton() {
        List<WebElement> containers = WebUtilities.findElements(driver, By.className("rz-panel-content"));
        for (WebElement container : containers) {
            if (container.findElement(By.tagName("h4")).getText().contains("Riepilogo Timbrature")) {
                container.findElement(GO_BACK_BUTTON).click();
                break;
            }
        }
    }
}
