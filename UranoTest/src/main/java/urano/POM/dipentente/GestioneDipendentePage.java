package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GestioneDipendentePage {
    private WebDriver driver;

    private static final By PAGE_TITLE = By.cssSelector(".rz-stack h4");
    private static final By DATA_GRID = By.className("rz-data-grid");
    private static final By CELLS_NAME = By.className("rz-sortable-column");
    private static final By FILTER_CELLS = By.className("rz-textbox");
    private static final By RESULT_CELLS = By.cssSelector(".rz-data-row p");
    private static final By RESULT_ROWS = By.className("rz-data-row");
    private static final By SELECT_DATE_BUTTON = By.cssSelector(".rz-unselectable-text .rz-button");
    private static final By APPLY_DATE_BUTTON = By.className("rz-apply-filter");
    private static final By BUTTONS = By.className("rz-button-md");
    private static final By CLEAR_FILTER_BUTTON = By.className("rz-cell-filter-clear");
    private static final By PAGINATOR = By.className("rz-paginator");
    private static final By NEXT_PAGE_BUTTON = By.className("rz-paginator-next");
    private static final By PAGE_BUTTONS = By.className("rz-paginator-page");

    public GestioneDipendentePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle() {
        return WebUtilities.findElement(driver, PAGE_TITLE);
    }

    public WebElement getDataGrid() {
        return WebUtilities.findElement(driver, DATA_GRID);
    }

    public WebElement getPaginator() {
        return WebUtilities.findElement(driver, PAGINATOR);
    }

    public int getAllRows() {
        List<WebElement> rows = WebUtilities.findElements(driver, RESULT_ROWS);
        return rows.size();
    }

    public int getNumberOfPages() {
        try {
            List<WebElement> pageButtons = WebUtilities.findElements(driver, PAGE_BUTTONS);
            return pageButtons.size();
        } catch(Exception e) {
            return 1;
        }
    }

    public String getSearchFilteredResult(String filter) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains(filter)) {
                position = i;
                break;
            }
        }
        WebElement filteredCell = WebUtilities.findElements(driver, RESULT_CELLS).get(position);
        return filteredCell.getText();
    }

    public List<String> getSearchFilteredResults(String filter) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains(filter)) {
                position = i;
                break;
            }
        }
        List<String> filteredResults = new ArrayList<>();
        List<WebElement> filteredRows = WebUtilities.findElements(driver, RESULT_ROWS);
        for(WebElement row : filteredRows) {
            List<WebElement> buCells = row.findElements(By.tagName("p"));
            String content = buCells.get(position).getText();
            filteredResults.add(content);
        }
        return filteredResults;
    }

    public void clickCreaDipendenteButton() {
        WebUtilities.clickElement(driver, By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/button[1]"));
    }

    public void clickClearFilterButton() {
        WebUtilities.clickElement(driver, CLEAR_FILTER_BUTTON);
    }

    public void clickNextPageButton() {
        WebUtilities.clickElement(driver, NEXT_PAGE_BUTTON);
    }

    public void clickFirstBozzaElement(String stato) {
        List<WebElement> buttons = WebUtilities.findElements(driver, By.cssSelector(".rz-data-row .rz-button-icon-only"));
        buttons.getFirst().click();
    }

    public void writeInDipendenteSearchBar(String name) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Dipendente")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.clear();
        filterBox.click();
        WebUtilities.typeWordLetterByLetter(driver, name);
    }

    // ACCOUNT = EMAIL
    public void writeInAccountSearchBar(String account) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Account")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.sendKeys(account);
    }

    public void writeInBusinessUnitSearchBar(String businessUnit) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Business Unit")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.sendKeys(businessUnit);
    }

    public void writeInTechLeadSearchBar(String techLead) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Tech Lead")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.clear();
        filterBox.click();
        WebUtilities.typeWordLetterByLetter(driver, techLead);
    }

    public void writeInSedeSearchBar(String sede) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Sede")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.sendKeys(sede);
    }

    public void writeInCittaResidenzaSearchBar(String cittaResidenza) {
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Città di residenza")) {
                position = i;
                break;
            }
        }
        WebElement filterBox = WebUtilities.findElements(driver, FILTER_CELLS).get(position);
        filterBox.sendKeys(cittaResidenza);
    }

    public void selectMonth4Filter(String month) {
        List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-label"));
        for (WebElement option : options) {
            String currentMonth = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("MMMM", Locale.ITALIAN));
            if (option.getText().equalsIgnoreCase(currentMonth)) {
                option.click();
            }
        }
        List<WebElement> months = WebUtilities.findElements(driver, By.cssSelector("[role='option']"));
        for (WebElement m : months) {
            if (m.getText().equalsIgnoreCase(month)) {
                m.click();
                return;
            }
        }
    }

    public void selectYear4Filter(String year) {
        List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-label"));
        for(WebElement option : options) {
            int currentYear = LocalDate.now().getYear();
            if(option.getText().equalsIgnoreCase(String.valueOf(currentYear))) {
                option.click();
            }
        }
        List<WebElement> years = WebUtilities.findElements(driver, By.cssSelector("[role='option']"));
        for(WebElement y : years) {
            if(y.getText().equalsIgnoreCase(year)) {
                y.click();
                return;
            }
        }
    }

    public void selectStartDay4Filter(String startDate) {
        List<WebElement> startDays = WebUtilities.findElements(driver, By.cssSelector(".rz-datepicker-calendar span"));
        for(WebElement d : startDays) {
            if(d.getText().equalsIgnoreCase(startDate)) {
                d.click();
                return;
            }
        }
    }

    public void selectDataAssunzione(String dataAssunzione) {
        String[] months = {"gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno",
                "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"};
        String day = dataAssunzione.split("/")[0];
        String month = dataAssunzione.split("/")[1];
        String monthName = months[Integer.parseInt(month) - 1];
        String year = dataAssunzione.split("/")[2];

        WebUtilities.clickElement(driver, SELECT_DATE_BUTTON);
        selectMonth4Filter(monthName);
        selectYear4Filter(year);
        selectStartDay4Filter(day);
        WebUtilities.scrollDownContinuously(driver);
        WebUtilities.clickElement(driver, APPLY_DATE_BUTTON);
    }

    public void selectStato(String stato){
        List<WebElement> cells = WebUtilities.findElements(driver, CELLS_NAME);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getText().contains("Stato")) {
                position = i;
                break;
            }
        }
        List<WebElement> boxes = WebUtilities.findElements(driver, By.className("rz-cell-filter-content"));
        boxes.get(position).click();
        List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-item"));
        for(WebElement option : options) {
            if(option.getText().equalsIgnoreCase(stato)) {
                option.click();
                return;
            }
        }
    }

    public boolean areAllBadgesOfType(String statoType) {
        List<WebElement> badges = WebUtilities.findElements(driver, By.className("rz-badge"));
        boolean allTheSame = true;
        for (WebElement badge : badges) {
            if (!badge.getText().equalsIgnoreCase(statoType)) {
                allTheSame = false;
            }
        }
        return allTheSame;
    }

    public void testFiltroTechLeadHelper(String searchTerm, String filterFor) {
        int retries = 0;
        int rawsBeforeFilter = countTotalRows();
        writeInTechLeadSearchBar(searchTerm);
        int rawsAfterFilter = 0;

        while (true) {
            rawsAfterFilter = countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca Tech Lead!";
            if (rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries++;
        }
        List<String> results = getSearchFilteredResults(filterFor);
        assert !results.isEmpty() : "Nessun risultato trovato per il filtro: " + filterFor;
        for (String result : results) {
            assert result.contains(searchTerm) : "Ricerca per: " + searchTerm + ", non riuscita o restituisce dati errati";
        }
    }

    private boolean isNextPageButtonClickable() {
        try {
            WebElement nextButton = driver.findElement(By.className("rz-paginator-next"));
            return nextButton.isDisplayed() && nextButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * Conta il numero totale di righe su tutte le pagine.
     */
    public int countTotalRows() {
        int totalRows = 0;
        int numOfPages = getNumberOfPages();
        if (numOfPages <= 1) {
            return getAllRows();
        }
        for (int i = 0; i < numOfPages; i++) {
            totalRows += getAllRows();
            if (i < numOfPages - 1 && isNextPageButtonClickable()) {
                clickNextPageButton();
            }
        }
        return totalRows;
    }
}
