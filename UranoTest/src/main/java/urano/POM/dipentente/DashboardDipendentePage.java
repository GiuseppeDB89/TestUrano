package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class DashboardDipendentePage {
    private WebDriver driver;

    private static final By MONTHS_YEARS_DROPDOWN = By.className("rz-splitbutton-menubutton");
    private static final By MONTHS_YEARS_OPTIONS = By.className("rz-menuitem");
    private static final By CERCA_BUTTON = By.className("rz-button-text");
    private static final By VISUAL_DETAILS_BOXES = By.className("rz-mb-3");
    private static final By JOB_NAME_CELLS = By.className("rz-frozen-cell");
    private static final By VIEW_DETAILS_BUTTON = By.className("rz-primary");
    private static final By GRID = By.className("rz-datatable");
    private static final By EMPLOYEE_NAME = By.cssSelector(".rz-stack h5");
    private static final By GO_BACK_BUTTON = By.cssSelector("[type='button']");
    private static final By ANNULLA_REPORT_BUTTON = By.className("rz-danger");
    private static final By RESOCONTO_ROWS = By.className("rz-accordion-header");
    private static final By ORE_LAVORO_ORDINARIO_ROW = By.className("rz-accordion-content");

    public DashboardDipendentePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getJobList() {
        List<WebElement> grids = WebUtilities.findElements(driver, GRID);
        return grids.getFirst();
    }

    public String getEmployeeName() {
        return WebUtilities.findElement(driver, EMPLOYEE_NAME).getText();
    }

    public WebElement getOreLavoroOrdRow() {
        return WebUtilities.findElement(driver, ORE_LAVORO_ORDINARIO_ROW);
    }

    public void clickOreLavoroOrdinario() {
        List<WebElement> rows = WebUtilities.findElements(driver, RESOCONTO_ROWS);
        for(WebElement row : rows) {
            if(row.getText().contains("Ore di Lavoro Ordinario")) {
                row.findElement(By.className("rzi-chevron-right")).click();
            }
        }
    }

    public void clickResocontoPresenzeDetailsButton() {
        List<WebElement> boxes = WebUtilities.findElements(driver, VISUAL_DETAILS_BOXES);
        for(WebElement box : boxes) {
            if(box.getText().contains("Resconto mensile presenze")) {
                box.findElement(VIEW_DETAILS_BUTTON).click();
                return;
            }
        }
    }

    public void clickRiepilogoTimbratureDetailsButton() {
        List<WebElement> boxes = WebUtilities.findElements(driver, VISUAL_DETAILS_BOXES);
        for(WebElement box : boxes) {
            if(box.getText().contains("RIEPILOGO TIMBRATURE")) {
                box.findElement(VIEW_DETAILS_BUTTON).click();
                return;
            }
        }
    }

    public void clickCalendarioPresenzeDetailsButton() {
        List<WebElement> boxes = WebUtilities.findElements(driver, VISUAL_DETAILS_BOXES);
        for(WebElement box : boxes) {
            if(box.getText().contains("Calendario Mensile")) {
                box.findElement(VIEW_DETAILS_BUTTON).click();
                return;
            }
        }
    }

    public void clickOreLavoroStraordinario() {
        List<WebElement> rows = WebUtilities.findElements(driver, RESOCONTO_ROWS);
        for(WebElement row : rows) {
            if(row.getText().contains("Ore di Lavoro Straordinario")) {
                row.findElement(By.className("rzi-chevron-right")).click();
                return;
            }
        }
    }

    public void clickOreAssenza() {
        List<WebElement> rows = WebUtilities.findElements(driver, RESOCONTO_ROWS);
        for(WebElement row : rows) {
            if(row.getText().contains("Ore di Assenza")) {
                row.findElement(By.className("rzi-chevron-right")).click();
                return;
            }
        }
    }

    public void clickGiorniAssenza() {
        List<WebElement> rows = WebUtilities.findElements(driver, RESOCONTO_ROWS);
        for(WebElement row : rows) {
            if(row.getText().contains("Giorni di Assenza")) {
                row.findElement(By.className("rzi-chevron-right")).click();
                return;
            }
        }
    }

    public void clickViewDetailsButtonByJob(String job) {
        List<WebElement> rows = WebUtilities.findElements(driver, JOB_NAME_CELLS);
        for(WebElement row : rows) {
            if(row.getText().contains(job)) {
                row.findElement(VIEW_DETAILS_BUTTON).click();
                return;
            }
        }
    }

    public void selectMonthsDropdownOption(String month) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, MONTHS_YEARS_DROPDOWN);
        dropdowns.getFirst().click();
        List<WebElement> options = WebUtilities.findElements(driver, MONTHS_YEARS_OPTIONS);
        for(WebElement option : options) {
            if(option.getText().contains(month)) {
                option.click();
                return;
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
                return;
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
            if (container.findElement(By.tagName("h4")).getText().contains("Dashboard Dipendente")) {
                container.findElement(GO_BACK_BUTTON).click();
                break;
            }
        }
    }

    public void clickAnnullaReport() {
        WebUtilities.clickElement(driver, ANNULLA_REPORT_BUTTON);
    }
}
