package urano.POM.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.ArrayList;
import java.util.List;

public class GestioneClientePage {
    private WebDriver driver;

    private static final By PAGE_TITLE = By.className("rz-text-h4");
    private static final By CREA_CLIENTE_BUTTON = By.id("button-crea-cliente");
    private static final By CLIENTE_GRID = By.className("rz-data-grid");
    private static final By ROWS = By.className("rz-data-row");
    private static final By CLEAR_FILTER_BUTTON = By.className("rz-cell-filter-clear");
    private static final By CLIENTE_CELLS = By.className("rz-cell-data");

    public GestioneClientePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle() {
        return WebUtilities.findElement(driver, PAGE_TITLE);
    }

    public WebElement getClientiGrid() {
        return WebUtilities.findElement(driver, CLIENTE_GRID);
    }

    public List<String> getClienteList() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if (condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Cliente")) {
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

    public List<String> getCodiceCliente() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if (condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Codice Cliente")) {
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

    public List<String> getSiglaCliente() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if (condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Sigla Cliente")) {
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

    public void writeCliente(String cliente) {
        boolean condition = false;
        int position = -1;
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Cliente")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        List<WebElement> cells = WebUtilities.findElements(driver, By.className("rz-textbox"));
        cells.get(position).sendKeys(cliente);
    }

    public void writeCodiceCliente(String codiceCliente) {
        boolean condition = false;
        int position = -1;
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Codice Cliente")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        List<WebElement> cells = WebUtilities.findElements(driver, By.className("rz-textbox"));
        cells.get(position).sendKeys(codiceCliente);
    }

    public void writeSiglaCliente(String siglaCliente) {
        boolean condition = false;
        int position = -1;
        WebElement table = WebUtilities.findElement(driver, CLIENTE_GRID);
        List<WebElement> heads = table.findElements(By.cssSelector("thead"));
        for (WebElement head : heads) {
            if(condition) break;
            List<WebElement> cells = head.findElements(By.cssSelector("th"));
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).getText().contains("Sigla Cliente")) {
                    position = i;
                    condition = true;
                    break;
                }
            }
        }
        assert position != -1 : "non trovato";
        List<WebElement> cells = WebUtilities.findElements(driver, By.className("rz-textbox"));
        cells.get(position).sendKeys(siglaCliente);
    }

    public void clickCreaClienteButton() {
        WebUtilities.clickElement(driver, CREA_CLIENTE_BUTTON);
    }

    public void clickClearFilter() {
        WebUtilities.clickElement(driver, CLEAR_FILTER_BUTTON);
    }

    public void clickClienteDetailsButton(String clienteName) {
        List<WebElement> rows = WebUtilities.findElements(driver, ROWS);
        for(WebElement row : rows) {
            try {
                String name = row.findElement(By.tagName("span")).getText();
                if(WebUtilities.isNameEqual(name, clienteName)) {
                    row.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }

    public boolean isClienteNameFilterOk(String expectedClienteName) {
        List<String> names = getClienteList();
        System.out.println(names);
        if (names.isEmpty()) {
            return false;
        }
        for (String name : names) {
            if (!name.equals(expectedClienteName)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCodiceClienteFilterOk(String expectedCodiceCliente) {
        List<String> codiceCliente = getCodiceCliente();
        if (codiceCliente.isEmpty()) {
            return false;
        }
        for (String codCli : codiceCliente) {
            if (!codCli.equals(expectedCodiceCliente)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSiglaClienteFilterOk(String expectedSiglaCliente) {
        List<String> siglaCliente = getSiglaCliente();
        if (siglaCliente.isEmpty()) {
            return false;
        }
        for (String siglCli : siglaCliente) {
            if (!siglCli.equals(expectedSiglaCliente)) {
                return false;
            }
        }
        return true;
    }
}
