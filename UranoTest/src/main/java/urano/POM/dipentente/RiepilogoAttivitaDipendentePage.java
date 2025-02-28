package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class RiepilogoAttivitaDipendentePage {
    private WebDriver driver;

    private static final By EMPLOYEE_NAME = By.className("rz-text-h5");
    private static final By DROPDOWN_BUTTONS = By.className("rz-splitbutton-menubutton");
    private static final By MONTHS_YEARS_OPTIONS = By.className("rz-menuitem");
    private static final By CERCA_BUTTON = By.className("rz-button-text");
    private static final By ID_COMM_OPTIONS = By.className("rz-menuitem");
    private static final By GRID = By.className("rz-data-grid");
    private static final By GO_BACK_BUTTON = By.cssSelector("[type='button']");

    public RiepilogoAttivitaDipendentePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmployeeName() {
        return WebUtilities.findElement(driver, EMPLOYEE_NAME).getText();
    }

    public WebElement getGrid() {
        return WebUtilities.findElement(driver, GRID);
    }

    public void selectIdentificativoCommessaButton(String IdCommessa) {
        List<WebElement> buttons = WebUtilities.findElements(driver, DROPDOWN_BUTTONS);
        WebUtilities.clickWebElement(driver, buttons.getFirst());
        List<WebElement> options = WebUtilities.findElements(driver, ID_COMM_OPTIONS);
        for(WebElement option : options) {
            if(option.getText().contains(IdCommessa)) {
                option.click();
                break;
            }
        }
    }

    public void selectMonthsDropdownOption(String month) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, DROPDOWN_BUTTONS);
        dropdowns.getFirst().click();
        List<WebElement> options = WebUtilities.findElements(driver, MONTHS_YEARS_OPTIONS);
        for(WebElement option : options) {
            if(option.getText().contains(month)) {
                option.click();
            }
        }
    }

    public void selectYearsDropdownOption(String year) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, DROPDOWN_BUTTONS);
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
            if (container.findElement(By.tagName("h4")).getText().contains("Riepilogo Attività")) {
                container.findElement(GO_BACK_BUTTON).click();
                break;
            }
        }

    }
}
