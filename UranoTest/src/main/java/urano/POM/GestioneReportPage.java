package urano.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class GestioneReportPage {
    private WebDriver driver;

    private static final By MONTHS_YEARS_DROPDOWN = By.className("rz-splitbutton-menubutton");
    private static final By MONTHS_YEARS_OPTIONS = By.className("rz-menuitem");
    private static final By EMPLOYEE_ROWS = By.className("rz-data-row");
    private static final By CELLS = By.className("rz-cell-data");
    private static final By VIEW_DETAILS_BUTTON = By.className("rz-button");
    private static final By EMPLOYEE_LIST = By.className("rz-data-grid");
    private static final By NEXT_PAGE_BUTTON = By.className("rz-paginator-next");

    public GestioneReportPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmployeeList() {
        return WebUtilities.findElement(driver, EMPLOYEE_LIST);
    }

    public void clickViewDetailsButtonByName(String employeeName) {
        boolean found = false;
        // Suddividi il nome completo in tutte le parole
        String[] nameParts = employeeName.split(" ");

        do {
            List<WebElement> rows = WebUtilities.findElements(driver, EMPLOYEE_ROWS);
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(CELLS);
                for (WebElement cell : cells) {
                    // Verifica se il testo della cella contiene tutte le parole del nome
                    boolean containsAllParts = true;
                    for (String part : nameParts) {
                        if (!cell.getText().contains(part)) {
                            containsAllParts = false;
                            break;
                        }
                    }
                    if (containsAllParts) {
                        row.findElement(VIEW_DETAILS_BUTTON).click();
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                WebUtilities.clickElement(driver, NEXT_PAGE_BUTTON);
            }
        } while (!found);
    }

    public void selectMonthsDropdownOption(String month) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, MONTHS_YEARS_DROPDOWN);
        dropdowns.getFirst().click();
        List<WebElement> options = WebUtilities.findElements(driver, MONTHS_YEARS_OPTIONS);
        for(WebElement option : options) {
            if(option.getText().contains(month)) {
                option.click();
            } else {
                WebUtilities.scrollToElement(driver, option);
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
}
