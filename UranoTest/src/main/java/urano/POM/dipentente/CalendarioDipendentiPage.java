package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import urano.utilities.WebUtilities;

import java.util.List;

public class CalendarioDipendentiPage {
    private WebDriver driver;

    private static final By BU_FILTER_SWITCH_BUTTON = By.className("rz-switch");
    private static final By BU_ROW = By.className("rz-group-row");
    private static final By GRID = By.className("rz-data-grid");
    private static final By BU_ROWS = By.className("rz-group-row");
    private static final By DROPDOWN = By.className("rz-dropdown");
    private static final By DROPDOWN_OPTIONS = By.className("rz-dropdown-item");
    private static final By CLEAR_OPTION_BUTTON = By.className("rz-dropdown-clear-icon");
    private static final By MONTHS_YEARS_DROPDOWN = By.className("rz-splitbutton-menubutton");
    private static final By MONTHS_YEARS_OPTIONS = By.className("rz-menuitem");
    private static final By CERCA_BUTTON = By.className("rz-button-text");
    private static final By EMPLOYEE_CELLS = By.className("rz-frozen-cell");
    private static final By VIEW_DETAILS_BUTTON = By.className("rz-button");

    public CalendarioDipendentiPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmployeeGrid() {
        return WebUtilities.findElement(driver, GRID);
    }

    public List<WebElement> getBuRowsType() {
        try{
            return WebUtilities.findElements(driver, BU_ROWS);
        } catch (Exception e) {
            return null;
        }
    }

    public List<WebElement> getBuEmployees() {
        return WebUtilities.findElements(driver, EMPLOYEE_CELLS);
    }

    public void clickBuFilterSwitchButton() {
        FluentWait<WebDriver> wait = WebUtilities.createFluentWait(driver, 10);
        wait.until(grid -> getEmployeeGrid().isDisplayed());
        WebUtilities.clickElement(driver, BU_FILTER_SWITCH_BUTTON);
    }

    public void clickBuArrow(String buName) {
        List<WebElement> rows = WebUtilities.findElements(driver, BU_ROW);
        for(WebElement row : rows) {
            if(row.getText().contains(buName)) {
                WebElement buttonToClick = row.findElement(By.tagName("a"));
                buttonToClick.click();
            }
        }
    }

    public void clickDropdownOptions() {
        WebUtilities.clickElement(driver, DROPDOWN);
        List<WebElement> options = WebUtilities.findElements(driver, DROPDOWN_OPTIONS);
        options.getFirst().click();
    }

    public void clickClearDropdownOption() {
        WebUtilities.clickElement(driver, CLEAR_OPTION_BUTTON);
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

    public void clickViewDetailsButtonByName(String name) {
        List<WebElement> cells = WebUtilities.findElements(driver, EMPLOYEE_CELLS);
        for (WebElement cell : cells) {
            String employeeName = "";
            if(!cell.getText().contains("Dipendente")) {
                employeeName = cell.findElement(By.cssSelector("p")).getText();
            }
            if (WebUtilities.isNameEqual(employeeName, name)) {
                cell.findElement(VIEW_DETAILS_BUTTON).click();
                return;
            }
        }
    }
}
