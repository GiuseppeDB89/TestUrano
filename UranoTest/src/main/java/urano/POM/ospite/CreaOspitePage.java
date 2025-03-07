package urano.POM.ospite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class CreaOspitePage {
    private WebDriver driver;

    private static final By NEW_USER_FORM = By.cssSelector(".rz-panel-content .rz-form");
    private static final By AZIENDA_INPUT = By.name("Azienda");
    private static final By NOME_INPUT = By.name("Nome");
    private static final By COGNOME_INPUT = By.name("Cognome");
    private static final By TELEFONO_INPUT = By.id("Telefono");
    private static final By EMAIL_INPUT = By.id("Email");
    private static final By GENERIC_INPUT = By.className("rz-col-12");
    private static final By HOST_OPTION = By.cssSelector("[role='option']");
    private static final By HOST_INPUT = By.className("rz-dropdown-filter");
    private static final By RESIDENZA_INPUT = By.id("Residenza");
    private static final By DATA_INIZIO_INPUT = By.id("DataInizio");
    private static final By DATA_FINE_INPUT = By.id("DataFine");
    private static final By MOTIVO_VISITA_INPUT = By.id("MotivoVisita");
    private static final By SALVA_BUTTON = By.cssSelector("[type='submit']");
    private static final By ERROR_MESSAGES = By.className("rz-messages-error");

    public CreaOspitePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNewUserForm() {
        return WebUtilities.findElement(driver, NEW_USER_FORM);
    }

    public void clickSalvaButton() {
        WebUtilities.clickElement(driver, SALVA_BUTTON);
    }

    public void writeAzienda(String azienda) {
        WebElement input = WebUtilities.findElement(driver, AZIENDA_INPUT);
        input.sendKeys(azienda);
    }

    public void writeNome(String nome) {
        WebElement input = WebUtilities.findElement(driver, NOME_INPUT);
        input.sendKeys(nome);
    }

    public void writeCognome(String cognome) {
        WebElement input = WebUtilities.findElement(driver, COGNOME_INPUT);
        input.sendKeys(cognome);
    }

    public void writeTelefono(String telefono) {
        WebElement input = WebUtilities.findElement(driver, TELEFONO_INPUT);
        input.sendKeys(telefono);
    }

    public void writeEmail(String email) {
        WebElement input = WebUtilities.findElement(driver, EMAIL_INPUT);
        input.sendKeys(email);
    }

    public void writeResidenza(String residenza) {
        WebElement input = WebUtilities.findElement(driver, RESIDENZA_INPUT);
        input.sendKeys(residenza);
    }

    public void writeDataInizio(String dataInizio) {
        WebElement input = WebUtilities.findElement(driver, DATA_INIZIO_INPUT);
        input.sendKeys(dataInizio);
    }

    public void writeDataFine(String dataFine) {
        WebElement input = WebUtilities.findElement(driver, DATA_FINE_INPUT);
        input.sendKeys(dataFine);
    }

    public void writeMotivoVisita(String motivoVisita) {
        WebElement input = WebUtilities.findElement(driver, MOTIVO_VISITA_INPUT);
        input.sendKeys(motivoVisita);
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
        dropdowns.get(position).click();
        List<WebElement> options = WebUtilities.findElements(driver, HOST_OPTION);
        for(WebElement option : options) {
            if(option.getText().contains(host)) {
                option.click();
                return;
            }
        }
    }

    public boolean isMandatoryFieldErrorDisplayed(String fieldName) {
        List<WebElement> inputs = WebUtilities.findElements(driver, GENERIC_INPUT);
        WebElement fieldToCheck = null;

        for (WebElement input : inputs) {
            WebElement label = input.findElement(By.tagName("label"));
            if (label.getText().contains(fieldName)) {
                fieldToCheck = input;
                break;
            }
        }
        if (fieldToCheck == null) {
            System.err.println("Il campo con nome " + fieldName + " non è stato trovato.");
            return false;
        }
        String fieldValue = fieldToCheck.getDomProperty("value");
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            try {
                WebElement errorElement = fieldToCheck.findElement(ERROR_MESSAGES);
                if (errorElement.isDisplayed() && errorElement.getText().contains("Campo obbligatorio")) {
                    return true;
                } else {
                    System.err.println("Il messaggio di errore non è comparso o non è corretto: " + errorElement.getText());
                }
            } catch (NoSuchElementException e) {
                System.err.println("Messaggio di errore non trovato per il campo: " + fieldName);
            }
        } else {
            System.err.println("Il campo " + fieldName + " non è vuoto: " + fieldValue);
        }
        return false;
    }
}
