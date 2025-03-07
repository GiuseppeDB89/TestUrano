package urano.POM.ospite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestioneOspitiPage {
    private WebDriver driver;

    private static final By OSPITI_GRID = By.className("rz-data-grid");
    private static final By CREA_OSPITE_BUTTON = By.cssSelector(".rz-mb-2 .rz-button");
    private static final By OSPITE_SEARCH_BAR = By.className("rz-textbox");
    // private static final By MOTIVO_SEARCH_BAR = By.id("#-VigCUP-gEMotivoVisita");
    private static final By DELETE_FILTER_BUTTON = By.className("rz-cell-filter-clear");
    private static final By DATE_FILTER_BUTTON = By.className("rz-variant-flat");
    private static final By OSPITE_NAME_CELLS = By.cssSelector(".rz-stack p");
    private static final By CALENDAR_DAYS = By.className("rz-state-default");
    private static final By APPLY_BUTTON = By.className("rz-apply-filter");
    private static final By CLEAR_BUTTON = By.className("rz-clear-filter");
    private static final By GRID_CELLS = By.className("rz-cell-data");
    private static final By CHECKBOX = By.className("rz-chkbox");
    private static final By BADGE = By.className("rz-badge");
    private static final By COLUMN_CELLS = By.className("rz-column-title");
    private static final By ROWS = By.className("rz-data-row");
    private static final By VIEW_BUTTONS = By.cssSelector(".rz-frozen-cell .rz-button");
    private static final By INSERT_DATE = By.className("rz-current-filter");

    public GestioneOspitiPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getOspitiGrid() {
        return WebUtilities.findElement(driver, OSPITI_GRID);
    }

    public List<WebElement> getBadges() {
        return WebUtilities.findElements(driver, BADGE);
    }

    public void clickOspiteByName(String fullName) {
        List<WebElement> cells = WebUtilities.findElements(driver, OSPITE_NAME_CELLS);
        int position = -1;
        for (int i = 0; i < cells.size(); i++) {
            if(cells.get(i).getText().contains(fullName)) {
                position = i;
                break;
            }
        }
        List<WebElement> viewButtons = WebUtilities.findElements(driver, VIEW_BUTTONS);
        viewButtons.get(position).click();
    }

    public List<String> getOspiteNamesList() {
        List<WebElement> ospiteNames = WebUtilities.findElements(driver, OSPITE_NAME_CELLS);
        List<String> result = new ArrayList<>();
        for(WebElement ospiteName : ospiteNames) {
            result.add(ospiteName.getText());
        }
        return result;
    }

    public List<String> getMotivoList() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, OSPITI_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for(WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for(int i = 0; i < cells.size(); i++) {
                if(cells.get(i).getText().contains("Motivo")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        for(WebElement row : WebUtilities.findElements(driver, ROWS)) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            valueRows.add(cells.get(position).getText());
        }
        return valueRows;
    }

    public List<String> getDataInizioList() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, OSPITI_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for(WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for(int i = 0; i < cells.size(); i++) {
                if(cells.get(i).getText().contains("Data Inizio")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        for(WebElement row : WebUtilities.findElements(driver, ROWS)) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            valueRows.add(cells.get(position).getText());
        }
        return valueRows;
    }

    public List<String> getDataFineList() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, OSPITI_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for(WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for(int i = 0; i < cells.size(); i++) {
                if(cells.get(i).getText().contains("Data Fine")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        for(WebElement row : WebUtilities.findElements(driver, ROWS)) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            valueRows.add(cells.get(position).getText());
        }
        return valueRows;
    }

    public List<String> getBadgeList() {
        return getBadges().stream().map(WebElement::getText).toList();
    }

    private static List<String> cleanList(List<String> list) {
        List<String> cleanedList = new ArrayList<>();
        for (String item : list) {
            if (item != null) {
                cleanedList.add(item.trim());
            }
        }
        return cleanedList;
    }

    public boolean isBadgeOk(String badgeType) {
        List<WebElement> badges = getBadges();
        boolean result = false;
        for(WebElement badge : badges) {
            if(badge.getText().equalsIgnoreCase(badgeType)) {
                result = true;
            } else {
                result = false;
                return result;
            }
        }
        return result;
    }

    public boolean isOspiteNameFilterOk(String expectedOspiteName) {
        List<WebElement> names = WebUtilities.findElements(driver, OSPITE_NAME_CELLS);
        for(WebElement name : names) {
            if(name.getText().contains(expectedOspiteName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOspiteMotivoFilterOk(String partialTitle) {
        List<WebElement> elements = driver.findElements(GRID_CELLS);
        for (WebElement element : elements) {
            String title = element.getDomProperty("title");
            if (title != null && title.contains(partialTitle)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStartDateFilterOk(String date) {
        List<WebElement> insertDates = WebUtilities.findElements(driver, By.className("rz-unselectable-text"));
        int position = -1;
        for (int i = 0; i < insertDates.size(); i++) {
            if(insertDates.get(i).getText().contains("Data Inizio")) {
                position = i;
                break;
            }
        }
        List<WebElement> cells = WebUtilities.findElements(driver, By.className("rz-cell-filter"));
        return cells.get(position).getText().contains(date);
    }

    public boolean isEndDateFilterOk(String date) {
        List<WebElement> insertDates = WebUtilities.findElements(driver, By.className("rz-unselectable-text"));
        int position = -1;
        for (int i = 0; i < insertDates.size(); i++) {
            if(insertDates.get(i).getText().contains("Data Fine")) {
                position = i;
                break;
            }
        }
        List<WebElement> cells = WebUtilities.findElements(driver, By.className("rz-cell-filter"));
        return cells.get(position).getText().contains(date);
    }

    public boolean areAllBadgeTypesPresent() {
        List<WebElement> badges = getBadges();
        boolean result = false;
        for(WebElement badge : badges) {
            if(badge.getText().equalsIgnoreCase("Abilitato") || badge.getText().equalsIgnoreCase("Non Abilitato")) {
                result = true;
            } else {
                result = false;
                System.out.println("Stato Badge non presente.");
                return result;
            }
        }
        return result;
    }

    public boolean isInAscendingOrder(List<String> list) {
        // Rimuove valori nulli o spazi e normalizza i dati
        List<String> cleanedList = cleanList(list);

        // Crea una copia della lista e la ordina
        List<String> sortedList = new ArrayList<>(cleanedList);
        sortedList.sort(String.CASE_INSENSITIVE_ORDER);

        // Confronta la lista originale con quella ordinata
        return cleanedList.equals(sortedList);
    }

    public boolean isInDescendingOrder(List<String> list) {
        // Rimuove valori nulli o spazi e normalizza i dati
        List<String> cleanedList = cleanList(list);

        // Crea una copia della lista e la ordina in ordine inverso
        List<String> sortedList = new ArrayList<>(cleanedList);
        sortedList.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        // Confronta la lista originale con quella ordinata
        return cleanedList.equals(sortedList);
    }

    private static List<LocalDate> parseDates(List<String> dateList) {
        List<LocalDate> dates = new ArrayList<>();
        for (String dateStr : dateList) {
            if (dateStr != null && !dateStr.isBlank()) {
                dates.add(LocalDate.parse(dateStr.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
        return dates;
    }

    public boolean areDateInAscendingOrder(List<String> dateList) {
        List<LocalDate> dates = parseDates(dateList);
        List<LocalDate> sortedDates = new ArrayList<>(dates);
        Collections.sort(sortedDates);
        return dates.equals(sortedDates);
    }

    public boolean areDateInDescendingOrder(List<String> dateList) {
        List<LocalDate> dates = parseDates(dateList);
        List<LocalDate> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Collections.reverseOrder());
        return dates.equals(sortedDates);
    }

    public boolean isNonAbilitatoBeforeAbilitato(List<String> badges) {
        boolean foundAbilitato = false;

        for (String badge : badges) {
            String normalizedBadge = badge.trim().toLowerCase(); // Normalizza maiuscole/minuscole e spazi
            if (normalizedBadge.equals("abilitato")) {
                foundAbilitato = true; // Una volta trovato "abilitato", attiviamo il flag
            } else if (normalizedBadge.equals("non abilitato") && foundAbilitato) {
                // Se troviamo "non abilitato" dopo aver trovato "abilitato", non è in ordine
                return false;
            }
        }
        return true; // Se il ciclo termina senza problemi, l'ordine è corretto
    }

    public void clickDeleteFilterButton() {
        WebUtilities.clickElement(driver, DELETE_FILTER_BUTTON);
    }

    public void clickStartDateFilter() {
        List<WebElement> buttons = WebUtilities.findElements(driver, DATE_FILTER_BUTTON);
        buttons.getFirst().click();
    }

    public void clickEndDateFilter() {
        List<WebElement> buttons = WebUtilities.findElements(driver, DATE_FILTER_BUTTON);
        buttons.getLast().click();
    }

    public void clickCheckbox() {
        WebUtilities.clickElement(driver, CHECKBOX);
    }

    public void clickSortButton(String columnName) {
        List<WebElement> buttons = WebUtilities.findElements(driver, COLUMN_CELLS);
        for(WebElement button : buttons) {
            if(button.getText().contains(columnName)) {
                button.findElement(By.className("rz-sortable-column-icon")).click();
            }
        }
    }

    public void clickApplyButton() {
        List<WebElement> buttons = WebUtilities.findElements(driver, APPLY_BUTTON);
        for (WebElement button : buttons) {
            if (button.isDisplayed()) {
                int attempts = 0;
                while (attempts < 10) {
                    try {
                        WebUtilities.clickWebElement(driver, button);
                        return;
                    } catch (Exception e) {
                        WebUtilities.scrollToElement(driver, button);
                        attempts++;
                    }
                }
            }
        }
        throw new AssertionError("Impossibile cliccare il pulsante APPLY.");
    }

    public void clickClearButton() {
        List<WebElement> buttons = WebUtilities.findElements(driver, CLEAR_BUTTON);
        for(WebElement button : buttons) {
            if(button.isDisplayed()) {
                button.click();
            }
        }
    }

    public void writeInOspiteSearchBar(String name) {
        List<WebElement> searchBars = WebUtilities.findElements(driver, OSPITE_SEARCH_BAR);
        searchBars.getFirst().clear();
        searchBars.getFirst().sendKeys(name);
    }

    public void writeInMotivoSearchBar(String motivo) {
        List<WebElement> searchBars = WebUtilities.findElements(driver, OSPITE_SEARCH_BAR);
        searchBars.getLast().clear();
        searchBars.getLast().sendKeys(motivo);
    }

    public void selectMonth4Filter(String month) {
        List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-label"));
        for(WebElement option : options) {
            Month currentMonth = LocalDate.now().getMonth();
            if(option.getText().equalsIgnoreCase(String.valueOf(currentMonth))) {
                option.click();
            }
        }
        List<WebElement> months = WebUtilities.findElements(driver, By.cssSelector("[role='option']"));
        for(WebElement m : months) {
            if(m.getText().equalsIgnoreCase(month)) {
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
        List<WebElement> allDays = WebUtilities.findElements(driver, CALENDAR_DAYS);
        List<WebElement> visibleDays = allDays.stream()
                .filter(WebElement::isDisplayed)
                .toList();
        for (WebElement day : visibleDays) {
            if (day.getText().equalsIgnoreCase(startDate)) {
                day.click();
                return;
            }
        }
        throw new AssertionError("Nessun giorno visibile corrisponde a: " + startDate);
    }

    public void selectEndDay4Filter(String endDate) {
        List<WebElement> allDays = WebUtilities.findElements(driver, CALENDAR_DAYS);
        List<WebElement> visibleDays = allDays.stream()
                .filter(WebElement::isDisplayed)
                .toList();
        for (WebElement day : visibleDays) {
            if (day.getText().equalsIgnoreCase(endDate)) {
                day.click();
                return;
            }
        }
        throw new AssertionError("Nessun giorno visibile corrisponde a: " + endDate);
    }
}
