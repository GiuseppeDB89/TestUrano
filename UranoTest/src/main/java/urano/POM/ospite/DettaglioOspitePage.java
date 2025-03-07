package urano.POM.ospite;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class DettaglioOspitePage {
    private WebDriver driver;

    private static final By GO_BACK_BUTTON = By.cssSelector("[type='button']");
    private static final By FORM = By.className("rz-form");
    private static final By FORM_BUTTONS = By.cssSelector(".rz-form .rz-button");
    private static final By AZIENDA_INPUT = By.name("Azienda");
    private static final By NOME_INPUT = By.name("Nome");
    private static final By COGNOME_INPUT = By.name("Cognome");
    private static final By TELEFONO_INPUT = By.id("Telefono");
    private static final By EMAIL_INPUT = By.id("Email");
    private static final By GENERIC_INPUT = By.className("rz-col-12");
    private static final By HOST_OPTION = By.cssSelector("[role='option']");
    private static final By HOST_INPUT = By.className("rz-dropdown-filter");
    private static final By CLEAR_HOST_BUTTON = By.className("rz-dropdown-clear-icon");
    private static final By RESIDENZA_INPUT = By.id("Residenza");
    private static final By DATA_INIZIO_INPUT = By.id("DataInizio");
    private static final By DATA_FINE_INPUT = By.id("DataFine");
    private static final By MOTIVO_VISITA_INPUT = By.id("MotivoVisita");
    private static final By SALVA_BUTTON = By.className("rz-success");
    private static final By ERROR_MESSAGES = By.className("rz-messages-error");
    private static final By ERROR_POPUP = By.className("rz-growl-item");
    private static final By SUCCESS_POPUP = By.className("rz-notification-message");

    public DettaglioOspitePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getForm() {
        return WebUtilities.findElement(driver, FORM);
    }

    public WebElement getSuccessMessage() {
        return WebUtilities.findElement(driver, SUCCESS_POPUP);
    }

    public WebElement getErrorPopUp() {
        return WebUtilities.findElement(driver, ERROR_POPUP);
    }

    public WebElement getErrorMessage() {
        return WebUtilities.findElement(driver, ERROR_MESSAGES);
    }

    public List<WebElement> getErrorMessages() {
        return WebUtilities.findElements(driver, ERROR_MESSAGES);
    }

    public String getAzienda() {
        WebElement input = WebUtilities.findElement(driver, AZIENDA_INPUT);
        return input.getDomProperty("value");
    }

    public String getNome() {
        WebElement input = WebUtilities.findElement(driver, NOME_INPUT);
        return input.getDomProperty("value");
    }

    public String getCognome() {
        WebElement input = WebUtilities.findElement(driver, COGNOME_INPUT);
        return input.getDomProperty("value");
    }

    public String getTelefono() {
        WebElement input = WebUtilities.findElement(driver, TELEFONO_INPUT);
        return input.getDomProperty("value");
    }

    public String getEmail() {
        WebElement input = WebUtilities.findElement(driver, EMAIL_INPUT);
        return input.getDomProperty("value");
    }

    public String getResidenza() {
        WebElement input = WebUtilities.findElement(driver, RESIDENZA_INPUT);
        return input.getDomProperty("value");
    }

    public String getDataInizio() {
        WebElement input = WebUtilities.findElement(driver, DATA_INIZIO_INPUT);
        return input.getDomProperty("value");
    }

    public String getDataFine() {
        WebElement input = WebUtilities.findElement(driver, DATA_FINE_INPUT);
        return input.getDomProperty("value");
    }

    public String getMotivoVisita() {
        WebElement input = WebUtilities.findElement(driver, MOTIVO_VISITA_INPUT);
        return input.getDomProperty("value");
    }

    public String getSelectedHost() {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, GENERIC_INPUT);
        int position = -1;
        for (int i = 0; i < dropdowns.size(); i++) {
            if (dropdowns.get(i).findElement(By.tagName("label")).getText().contains("Host*")) {
                position = i;
                break;
            }
        }
        WebElement dropdown = dropdowns.get(position);
        return dropdown.findElement(By.className("rz-dropdown-label")).getText();
    }
    
    public void clickModificaButton() {
        List<WebElement> buttons = WebUtilities.findElements(driver, FORM_BUTTONS);
        for(WebElement button : buttons) {
            if(button.findElement(By.className("rz-button-text")).getText().contains("Modifica Ospite")) {
                button.click();
                break;
            }
        }
    }

    public void clickGoBackButton() {
        List<WebElement> containers = WebUtilities.findElements(driver, By.className("rz-panel-content"));
        for (WebElement container : containers) {
            if (container.findElement(By.cssSelector("h4")).getText().contains("Dettaglio Ospite")) {
                container.findElement(GO_BACK_BUTTON).click();
                break;
            }
        }
    }

    public void clickSalvaButton() {
        WebUtilities.clickElement(driver, SALVA_BUTTON);
    }

    public void writeAzienda(String azienda) {
        WebElement input = WebUtilities.findElement(driver, AZIENDA_INPUT);
        input.clear();
        input.sendKeys(azienda);
    }

    public void writeNome(String nome) {
        WebElement input = WebUtilities.findElement(driver, NOME_INPUT);
        input.clear();
        input.sendKeys(nome);
    }

    public void writeCognome(String cognome) {
        WebElement input = WebUtilities.findElement(driver, COGNOME_INPUT);
        input.clear();
        input.sendKeys(cognome);
    }

    public void writeTelefono(String telefono) {
        WebElement input = WebUtilities.findElement(driver, TELEFONO_INPUT);
        input.clear();
        input.sendKeys(telefono);
    }

    public void writeEmail(String email) {
        WebElement input = WebUtilities.findElement(driver, EMAIL_INPUT);
        input.clear();
        input.sendKeys(email);
    }

    public void writeResidenza(String residenza) {
        WebElement input = WebUtilities.findElement(driver, RESIDENZA_INPUT);
        input.clear();
        input.sendKeys(residenza);
    }

    public void writeDataInizio(String dataInizio) {
        WebElement input = WebUtilities.findElement(driver, DATA_INIZIO_INPUT);
        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(dataInizio);
    }

    public void writeDataFine(String dataFine) {
        WebElement input = WebUtilities.findElement(driver, DATA_FINE_INPUT);
        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(dataFine);
    }

    public void writeMotivoVisita(String motivoVisita) {
        WebElement input = WebUtilities.findElement(driver, MOTIVO_VISITA_INPUT);
        input.clear();
        input.sendKeys(motivoVisita);
    }

    public void clickClearHostButton() {
        WebUtilities.clickElement(driver, CLEAR_HOST_BUTTON);
    }

    public void selectHostFromDropdown(String host) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, GENERIC_INPUT);
        int position = -1;
        for (int i = 0; i < dropdowns.size(); i++) {
            if(dropdowns.get(i).findElement(By.tagName("label")).getText().contains("Host*")) {
                position = i;
                break;
            }
        }
        clickClearHostButton();
        dropdowns.get(position).click();
        List<WebElement> options = WebUtilities.findElements(driver, HOST_OPTION);
        for(WebElement option : options) {
            if(option.getText().contains(host)) {
                option.click();
                return;
            }
        }
    }
}
