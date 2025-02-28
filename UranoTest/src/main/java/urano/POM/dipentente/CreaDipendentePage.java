package urano.POM.dipentente;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import urano.utilities.WebUtilities;

import java.util.ArrayList;
import java.util.List;

public class CreaDipendentePage {
    private WebDriver driver;

    private static final By PAGE_TITLE = By.cssSelector(".rz-stack h4");
    private static final By STEPS = By.className("rz-steps");
    private static final By STEP_BUTTONS = By.className("rz-menuitem-link");
    private static final By ERROR_MESSAGES = By.className("rz-messages-error");
    private static final By GENERIC_INPUT = By.className("rz-col-12");
    private static final By NAME_BOX = By.id("Nome");
    private static final By COGNOME_BOX = By.id("Cognome");
    private static final By CODICE_FISCALE_BOX = By.id("CodiceFiscale");
    private static final By ESTERO_CHECKBOX = By.className("rz-chkbox-box");
    private static final By INPUT = By.className("rz-form-field-content");
    private static final By DATA_NASCITA_BOX = By.id("DataNascita");
    private static final By INDIRIZZO_BOX = By.id("Indirizzo(Via, Civico)");
    private static final By DESCRIZIONE_BOX = By.id("Descrizione");
    private static final By EMAIL_BOX = By.id("Email Personale");
    private static final By TELEFONO_BOX = By.id("Telefono Personale");
    private static final By EMAIL_AZIENDALE = By.id("EmailAziendale");
    private static final By TELEFONO_AZIENDALE = By.id("TelefonoAziendale");
    private static final By DOCUMENTO_IDENTITA = By.className("rz-fileupload");
    private static final By MATRICOLA_TICKET = By.id("MatricolaTicket");
    private static final By DATA_ABILITAZIONE = By.id("DataAbilitazione");
    private static final By BANCA_ACCREDITO = By.id("BancaAccredito");
    private static final By IBAN = By.id("IBAN");
    private static final By SALVA_CHIUDI_BUTTON = By.cssSelector(".rz-col-md-2 .rz-button-text");
    private static final By SALVA_PROCEDI_BUTTON = By.className("rz-steps-next");

    public CreaDipendentePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle() {
        return WebUtilities.findElement(driver, PAGE_TITLE);
    }

    public WebElement getSteps() {
        return WebUtilities.findElement(driver, STEPS);
    }

    public List<WebElement> getErrorMessages() {
        List<WebElement> mandatoryFieldsMessage = new ArrayList<>();
        List<WebElement> errorMessages = WebUtilities.findElements(driver, ERROR_MESSAGES);
        for(WebElement errorMessage : errorMessages) {
            if(errorMessage.getText().contains("Campo obbligatorio")) {
                mandatoryFieldsMessage.add(errorMessage);
            }
        }
        return mandatoryFieldsMessage;
    }

    public void writeName(String name) {
        WebUtilities.findElement(driver, NAME_BOX).sendKeys(name);
    }

    public void writeSurname(String surname) {
        WebUtilities.findElement(driver, COGNOME_BOX).sendKeys(surname);
    }

    public void writeCodiceFiscale(String codFiscale) {
        WebUtilities.findElement(driver, CODICE_FISCALE_BOX).sendKeys(codFiscale);
    }

    public void clickEsteroCheckbox() {
        WebUtilities.clickElement(driver, ESTERO_CHECKBOX);
    }

    public void insertProvinciaNascita(String provNascita) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            WebElement label = WebUtilities.findElementOnParent(driver, dropdown, By.className("rz-form-field-label"));
            if(label.getText().contains("Provincia di Nascita")) {
                dropdown.click();
                WebUtilities.typeWordLetterByLetter(driver, provNascita);
            }
        }
        List<WebElement> rightDropdowns = WebUtilities.findElements(driver, By.className("rz-dropdown-panel"));
        for(WebElement rightDropdown : rightDropdowns) {
            List<WebElement> options = rightDropdown.findElements(By.className("rz-dropdown-item"));
            for (WebElement option : options) {
                if (option.getText().contains(provNascita)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void insertComuneNascita(String comuneNascita) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            WebElement label = WebUtilities.findElementOnParent(driver, dropdown, By.className("rz-form-field-label"));
            if(label.getText().contains("Comune di Nascita")) {
                dropdown.click();
                WebUtilities.typeWordLetterByLetter(driver, comuneNascita);
            }
        }
        List<WebElement> rightDropdowns = WebUtilities.findElements(driver, By.className("rz-dropdown-panel"));
        for(WebElement rightDropdown : rightDropdowns) {
            List<WebElement> options = rightDropdown.findElements(By.className("rz-dropdown-item"));
            for (WebElement option : options) {
                if (option.getText().contains(comuneNascita)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void writeDataNascita(String dataNascita) {
        WebUtilities.findElement(driver, DATA_NASCITA_BOX).sendKeys(dataNascita);
    }

    public void selectGenere(String genere) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Genere")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(genere)) {
                option.click();
                break;
            }
        }
    }

    public void insertProvinciaResidenza(String provResidenza) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            WebElement label = WebUtilities.findElementOnParent(driver, dropdown, By.className("rz-form-field-label"));
            if(label.getText().contains("Provincia di Residenza")) {
                dropdown.click();
                WebUtilities.typeWordLetterByLetter(driver, provResidenza);
            }
        }
        List<WebElement> rightDropdowns = WebUtilities.findElements(driver, By.className("rz-dropdown-panel"));
        for(WebElement rightDropdown : rightDropdowns) {
            List<WebElement> options = rightDropdown.findElements(By.className("rz-dropdown-item"));
            for (WebElement option : options) {
                if (option.getText().contains(provResidenza)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void insertComuneResidenza(String comuneResidenza) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            WebElement label = WebUtilities.findElementOnParent(driver, dropdown, By.className("rz-form-field-label"));
            if(label.getText().contains("Comune di Residenza")) {
                dropdown.click();
                WebUtilities.typeWordLetterByLetter(driver, comuneResidenza);
            }
        }
        List<WebElement> rightDropdowns = WebUtilities.findElements(driver, By.className("rz-dropdown-panel"));
        for(WebElement rightDropdown : rightDropdowns) {
            List<WebElement> options = rightDropdown.findElements(By.className("rz-dropdown-item"));
            for (WebElement option : options) {
                if (option.getText().contains(comuneResidenza)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public void writeIndirizzo(String indirizzo) {
        WebUtilities.findElement(driver, INDIRIZZO_BOX).sendKeys(indirizzo);
    }

    public void selectTitoloStudio(String titoloStudio) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Titolo di Studio")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(titoloStudio)) {
                option.click();
                break;
            }
        }
    }

    public void writeDescrizione(String descrizione) {
        WebUtilities.findElement(driver, DESCRIZIONE_BOX).sendKeys(descrizione);
    }

    public void writeEmail(String email) {
        WebUtilities.findElement(driver, EMAIL_BOX).sendKeys(email);
    }

    public void writeTelefono(String telefono) {
        WebUtilities.findElement(driver, TELEFONO_BOX).sendKeys(telefono);
    }

    public void clickInformazioniDipendete() {
        List<WebElement> stepButtons = WebUtilities.findElements(driver, STEP_BUTTONS);
        for(WebElement step : stepButtons) {
            if (step.getText().contains("Informazioni del Dipendente")) {
                step.click();
                break;
            }
        }
    }

    public void selectJobPosition(String jobPosition) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(WebUtilities.findElementOnParent(driver, dropdown,By.className("rz-form-field-label")).getText().contains("Job Position")) {
                WebUtilities.clickWebElement(driver, dropdown);
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(jobPosition)) {
                option.click();
                break;
            }
        }
    }

    public void selectBusinessUnit(String bu) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Business Unit")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(bu)) {
                option.click();
                break;
            }
        }
    }

    public void selectSede(String sede) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Sede")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(sede)) {
                option.click();
                break;
            }
        }
    }

    public void writeEmailAziendale(String emailAziendale) {
        WebUtilities.findElement(driver, EMAIL_AZIENDALE).sendKeys(emailAziendale);
    }

    public void writeTelefonoAziendale(String telefonoAziendale) {
        WebUtilities.findElement(driver, TELEFONO_AZIENDALE).sendKeys(telefonoAziendale);
    }

    public void insertDocumento(String documentoPathFile) {
        FluentWait<WebDriver> wait = WebUtilities.createFluentWait(driver, 10);
        WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("DocumentoIdentitaName")));
        uploadElement.sendKeys(documentoPathFile);
    }


    public void selectResponsabile(String responsabile) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Responsabile")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(responsabile)) {
                option.click();
                break;
            }
        }
    }

    public void selectApprovatore(String approvatore) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Approvatore Fisso")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(approvatore)) {
                option.click();
                break;
            }
        }
    }

    public void selectStatoTicket(String statoTicket) {
        List<WebElement> dropdowns = WebUtilities.findElements(driver, INPUT);
        for(WebElement dropdown : dropdowns) {
            if(dropdown.findElement(By.className("rz-form-field-label")).getText().contains("Stato Ticket")) {
                dropdown.click();
            }
        }
        List<WebElement> options = WebUtilities.findElements(driver, By.cssSelector(".rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(statoTicket)) {
                option.click();
                break;
            }
        }
    }

    public void writeMatricolaTicket(String matricolaTicket) {
        WebUtilities.findElement(driver, MATRICOLA_TICKET).sendKeys(matricolaTicket);
    }

    public void writeDataAbilitazione(String dataAbilitazione) {
        WebUtilities.findElement(driver, DATA_ABILITAZIONE).sendKeys(dataAbilitazione);
    }

    public void writeBancaAccredito(String bancaAccredito) {
        WebUtilities.findElement(driver, BANCA_ACCREDITO).sendKeys(bancaAccredito);
    }

    public void writeIban(String iban) {
        WebUtilities.findElement(driver, IBAN).sendKeys(iban);
    }

    public void clickSalvaChiudiButton() {
        WebUtilities.clickElement(driver, SALVA_CHIUDI_BUTTON);
    }

    public void clickSalvaProcediButton() {
        WebUtilities.clickElement(driver, SALVA_PROCEDI_BUTTON);
    }

    public void clickGoBackButton() {
        List<WebElement> stacks = WebUtilities.findElements(driver, By.className("rz-stack"));
        for(WebElement stack : stacks) {
            try {
                WebElement titleElement = stack.findElement(By.tagName("h4"));
                if (titleElement.getText().contains("Crea Dipendente")) {
                    stack.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
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
