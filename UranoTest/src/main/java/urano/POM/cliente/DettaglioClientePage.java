package urano.POM.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.ArrayList;
import java.util.List;

public class DettaglioClientePage {
    private WebDriver driver;

    private static final By CLIENTE_NAME = By.cssSelector(".rz-col-12 h4");
    private static final By MODIFY_BUTTON = By.id("button-modifica");
    private static final By FORM = By.className("rz-dialog");
    private static final By NOME_CLIENTE_INPUT = By.id("NomeCliente");
    private static final By CODICE_CLIENTE_INPUT = By.id("CodiceCliente");
    private static final By SIGLA_CLIENTE_INPUT = By.id("SiglaCliente");
    private static final By SALES_ACCOUNT_INPUT = By.id("SalesAccount");
    private static final By SALES_ACCOUNT_EMAIL_INPUT = By.id("SalesAccount");
    private static final By PROTOCOLLO_INPUT = By.id("Protocollo");
    private static final By DROPDOWN_INPUT = By.className("rz-form-field-content");
    private static final By NOTE_INPUT = By.id("Note");
    private static final By SALVA_BUTTON = By.id("button-salva");
    private static final By CLEAR_FILTER_BUTTON = By.className("rz-cell-filter-clear");
    private static final By COL = By.cssSelector(".rz-col-12");
    private static final By DATA_ROWS = By.className("rz-data-row");
    private static final By CLIENTE_CELLS = By.className("rz-cell-data");

    public DettaglioClientePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getClienteName() {
        return WebUtilities.findElement(driver, CLIENTE_NAME).getText();
    }

    public WebElement getForm() {
        return WebUtilities.findElement(driver, FORM);
    }

    public void clickModifyButton() {
        WebUtilities.clickElement(driver, MODIFY_BUTTON);
    }

    public void clickSalvaButton() {
        WebUtilities.clickElement(driver, SALVA_BUTTON);
    }

    public void clickClearFilter() {
        WebUtilities.clickElement(driver, CLEAR_FILTER_BUTTON);
    }

    public void clickGoBackButton() {
        List<WebElement> stacks = WebUtilities.findElements(driver, By.className("rz-stack"));
        for(WebElement stack : stacks) {
            try {
                WebElement titleElement = stack.findElement(By.tagName("h4"));
                if (titleElement.getText().contains("Dettaglio Cliente")) {
                    stack.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }

    public void writeCliente(String cliente) {
        WebElement input = driver.findElement(NOME_CLIENTE_INPUT);
        input.clear();
        input.sendKeys(cliente);
    }

    public void writeCodiceCliente(String codCliente) {
        WebElement input = driver.findElement(CODICE_CLIENTE_INPUT);
        input.clear();
        input.sendKeys(codCliente);
    }

    public void writSiglaCliente(String siglaCliente) {
        WebElement input = driver.findElement(SIGLA_CLIENTE_INPUT);
        input.clear();
        input.sendKeys(siglaCliente);
    }

    public void writeSalesAccount(String salesAccount) {
        WebElement input = driver.findElement(SALES_ACCOUNT_INPUT);
        input.clear();
        input.sendKeys(salesAccount);
    }

    public void writeSAEmail(String saEmail) {
        WebElement input = driver.findElement(SALES_ACCOUNT_EMAIL_INPUT);
        input.clear();
        input.sendKeys(saEmail);
    }

    public void writeProtocollo(String protocollo) {
        WebElement input = driver.findElement(PROTOCOLLO_INPUT);
        input.clear();
        input.sendKeys(protocollo);
    }

    public void selectTerminiPagamento(String terminePagamento) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, DROPDOWN_INPUT);
        for (WebElement dropdown : dropdowns) {
            if (dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Termini Pagamento")) {
                dropdown.click();
                List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-item"));
                for (WebElement option : options) {
                    if (option.getText().contains(terminePagamento)) {
                        option.click();
                    }
                    break;
                }
            }
        }
    }

    public void selectTempiPagamento(String tempiPagamento) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, DROPDOWN_INPUT);
        for (WebElement dropdown : dropdowns) {
            if (dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Tempi Pagamento")) {
                dropdown.click();
                List<WebElement> options = dropdown.findElements(By.className("rz-dropdown-item"));
                for (WebElement option : options) {
                    if (option.getText().contains(tempiPagamento)) {
                        option.click();
                    }
                    break;
                }
            }
        }
    }

    public void writeNote (String note){
        WebElement input = driver.findElement(NOTE_INPUT);
        input.clear();
        input.sendKeys(note);
    }

    public void writeCodiceOrdine(String codOrdine) {
        boolean condition = false;
        int position = -1;
        List<WebElement> cols = WebUtilities.findElements(driver, COL);
        for(WebElement col : cols) {
            if(col.findElement(By.tagName("h6")).getText().contains("Storico Ordini")) {
                List<WebElement> cells = col.findElements(By.cssSelector("thead .rz-column-title-content"));
                for (int i = 0; i < cells.size(); i++) {
                    if (cells.get(i).getText().contains("Codice Ordine")) {
                        position = i;
                        condition = true;
                        break;
                    }
                }
            }
        }
        assert position != -1 : "non trovato";
        List<WebElement> textboxes = WebUtilities.findElements(driver, By.className("rz-textbox"));
        textboxes.get(position).sendKeys(codOrdine);
    }

    public void writeImporto(String importo) {
        List<WebElement> textboxes = WebUtilities.findElements(driver, By.className("rz-state-empty"));
        textboxes.getLast().sendKeys(importo);
    }

    public void selectDataEmissione(String dataEmissione) {
        int position = -1;
        List<WebElement> cols = WebUtilities.findElements(driver, COL);
        for (WebElement col : cols) {
            if (col.findElement(By.tagName("h6")).getText().contains("Storico Ordini")) {
                List<WebElement> cells = col.findElements(By.cssSelector("thead .rz-column-title-content"));
                for (int i = 0; i < cells.size(); i++) {
                    if (cells.get(i).getText().contains("Data Emissione")) {
                        position = i;
                        break;
                    }
                }
            }
        }
        assert position != -1 : "Colonna Importo non trovata";
        List<WebElement> rows = WebUtilities.findElements(driver, DATA_ROWS);
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            cells.get(position).click();
        }

    }

    public List<String> getImporto() {
        int position = -1;
        List<String> importi = new ArrayList<>();
        List<WebElement> cols = WebUtilities.findElements(driver, COL);
        for (WebElement col : cols) {
            if (col.findElement(By.tagName("h6")).getText().contains("Storico Ordini")) {
                List<WebElement> cells = col.findElements(By.cssSelector("thead .rz-column-title-content"));
                for (int i = 0; i < cells.size(); i++) {
                    if (cells.get(i).getText().contains("Importo")) {
                        position = i;
                        break;
                    }
                }
            }
        }
        assert position != -1 : "Colonna Importo non trovata";
        List<WebElement> rows = WebUtilities.findElements(driver, DATA_ROWS);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            importi.add(cells.get(position).getText());
        }
        return importi;
    }

    public List<String> getCodiceOrdine() {
        boolean condition = false;
        int position = -1;
        List<String> valueRows = new ArrayList<>();
        List<WebElement> cols = WebUtilities.findElements(driver, COL);
        for(WebElement col : cols) {
            if(col.findElement(By.tagName("h6")).getText().contains("Storico Ordini")) {
                List<WebElement> cells = col.findElements(By.cssSelector("thead .rz-column-title-content"));
                for (int i = 0; i < cells.size(); i++) {
                    if (cells.get(i).getText().contains("Codice Ordine")) {
                        position = i;
                        condition = true;
                        break;
                    }
                }
            }
        }
        assert position != -1 : "non trovato";
        List<WebElement> rows = WebUtilities.findElements(driver, DATA_ROWS);
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            valueRows.add(cells.get(position).getText());
        }
        return valueRows;
    }

    public boolean isCodiceClienteFilterOk(String expectedCodiceCliente) {
        List<WebElement> clienti = WebUtilities.findElements(driver, CLIENTE_CELLS);
        if (clienti.isEmpty()) {
            return false;
        }
        for (WebElement cliente : clienti) {
            if (!cliente.getText().contains(expectedCodiceCliente)) {
                // Se un elemento non rispetta il filtro, restituisci false
                return false;
            }
        }
        return true;
    }
}
