package urano.POM.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class SideNavbar {
    private WebDriver driver;

    private static final By HOMEPAGE = By.cssSelector("a.active");
    private static final By GENERAL_BUTTON = By.className("rz-navigation-item-text");
    private static final By GESTIONE_REPORT = By.cssSelector("[href='report/gestione']");
    private static final By CALENDARIO_DIPENDENTI = By.cssSelector("[href='dipendenti/calendarioDipendenti']");
    private static final By GESTIONE_TIMBRATURA = By.cssSelector("[href='timbrature/gestione']");
    private static final By GESTIONE_OSPITI = By.cssSelector("[href='ospiti/gestione']");
    private static final By CREA_OSPITE = By.cssSelector("[href='ospiti/crea']");
    private static final By GESTIONE_DIPENDENTE = By.cssSelector("[href='dipendenti/gestione']");
    private static final By CREA_DIPENDENTE = By.cssSelector("[href='dipendenti/crea']");
    private static final By ASSEGNA_TECH_LEAD = By.cssSelector("[href='dipendenti/AssegnaTechLead']");
    private static final By GESTIONE_CANDIDATO = By.cssSelector("[href='candidati/gestione']");
    private static final By CREA_CANDIDATO = By.cssSelector("[href='candidati/crea']");
    private static final By ARCHIVIO = By.cssSelector("[href='candidati/gestione/archivio']");
    private static final By ACCADEMY = By.cssSelector("[href='candidati/accademy']");
    private static final By GESTIONE_CLIENTE = By.cssSelector("[href='clienti/gestione']");
    private static final By CREA_CLIENTE = By.cssSelector("[href='clienti/crea']");
    private static final By CONSULENZA_GESTIONE_RICHIESTA = By.cssSelector("[href='consulenza/richieste/gestione']");
    private static final By CONSULENZA_REGISTRA_RICHIESTA = By.cssSelector("[href='consulenza/richieste/crea']");
    private static final By GESTIONE_ORDINE = By.cssSelector("[href='ordini/gestione']");
    private static final By CREA_ORDINE = By.cssSelector("[href='ordini/crea']");
    private static final By GESTIONE_COMMESSA = By.cssSelector("[href='commesse/gestione']");
    private static final By CREA_COMMESSA = By.cssSelector("[href='commesse/crea']");
    private static final By PRE_FATTURAZIONI = By.cssSelector("[href='prefatturazioni/gestione']");
    private static final By PRE_FATTURAZIONI_AMM = By.cssSelector("[href='prefatturazioni/gestioneAmministrazione']");
    private static final By GESTIONE_RICHIESTA = By.cssSelector("[href='richieste/gestione']");
    private static final By CARICA_RICHIESTA = By.cssSelector("[href='richieste/crea']");
    private static final By GESTIONE_RIMBORSO = By.cssSelector("[href='rimborsi/gestione']");
    private static final By CARICA_RIMBORSO = By.cssSelector("[href='rimborsi/crea']");
    private static final By BUSTA_PAGA_CARICA_TUTTE = By.cssSelector("[href='bustepaga']");
    private static final By BUSTA_PAGA_CARICA_SINGOLA = By.cssSelector("[href='bustepaga']");
    private static final By CERT_UNICA_CARICA_TUTTE = By.cssSelector("[href='bustepaga']");
    private static final By CERT_UNICA_CARICA_SINGOLA = By.cssSelector("[href='bustepaga']");
    private static final By FESTIVITA_E_CHIUSURE = By.cssSelector("[href='festivita/gestione']");
    private static final By CONVENZIONI = By.cssSelector("[href='path']");
    private static final By DOCUMENTI = By.cssSelector("[href='dropdown-datagrid']");

    public SideNavbar(WebDriver driver) {
        this.driver = driver;
    }

    public void getHomepage() {
        WebUtilities.clickElement(driver, HOMEPAGE);
    }

    public void clickReportButton() {
        List<WebElement> buttons = WebUtilities.findElements(driver, GENERAL_BUTTON);
        for(WebElement button : buttons) {
            if(button.getText().contains("Report")) {
                button.click();
            }
        }
    }

    public void clickGestioneReport() {
        WebUtilities.clickElement(driver, GESTIONE_REPORT);
    }

    public void clickCalendarioDipendenti() {
        WebUtilities.clickElement(driver, CALENDARIO_DIPENDENTI);
    }

    public void clickGestioneTimbratura() {
        WebUtilities.clickElement(driver, GESTIONE_TIMBRATURA);
    }

    public void clickGestioneOspiti() {
        WebUtilities.clickElement(driver, GESTIONE_OSPITI);
    }

    public void clickCreaOspite() {
        WebUtilities.clickElement(driver, CREA_OSPITE);
    }

    public void clickGestioneDipendente() {
        WebUtilities.clickElement(driver, GESTIONE_DIPENDENTE);
    }

    public void clickCreaDipendente() {
        WebUtilities.clickElement(driver, CREA_DIPENDENTE);
    }

    public void clickAssegnaTechLead() {
        WebUtilities.clickElement(driver, ASSEGNA_TECH_LEAD);
    }

    public void clickGestioneCandidato() {
        WebUtilities.clickElement(driver, GESTIONE_CANDIDATO);
    }

    public void clickCreaCandidato() {
        WebUtilities.clickElement(driver, CREA_CANDIDATO);
    }

    public void clickArchivio() {
        WebUtilities.clickElement(driver, ARCHIVIO);
    }

    public void clickAccademy() {
        WebUtilities.clickElement(driver, ACCADEMY);
    }

    public void clickGestioneCliente() {
        WebUtilities.clickElement(driver, GESTIONE_CLIENTE);
    }

    public void clickCreaCliente() {
        WebUtilities.clickElement(driver, CREA_CLIENTE);
    }

    public void clickConsulenzaGestioneRichiesta() {
        WebUtilities.clickElement(driver, CONSULENZA_GESTIONE_RICHIESTA);
    }

    public void clickConsulenzaRegistraRichiesta() {
        WebUtilities.clickElement(driver, CONSULENZA_REGISTRA_RICHIESTA);
    }

    public void clickGestioneOrdine() {
        WebUtilities.clickElement(driver, GESTIONE_ORDINE);
    }

    public void clickCreaOrdine() {
        WebUtilities.clickElement(driver, CREA_ORDINE);
    }

    public void clickGestioneCommessa() {
        WebUtilities.clickElement(driver, GESTIONE_COMMESSA);
    }

    public void clickCreaCommessa() {
        WebUtilities.clickElement(driver, CREA_COMMESSA);
    }

    public void clickPreFatturazioni() {
        WebUtilities.clickElement(driver, PRE_FATTURAZIONI);
    }

    public void clickPreFatturazioniAmm() {
        WebUtilities.clickElement(driver, PRE_FATTURAZIONI_AMM);
    }

    public void clickGestioneRichiesta() {
        WebUtilities.clickElement(driver, GESTIONE_RICHIESTA);
    }

    public void clickCaricaRichiesta() {
        WebUtilities.clickElement(driver, CARICA_RICHIESTA);
    }

    public void clickGestioneRimborso() {
        WebUtilities.clickElement(driver, GESTIONE_RIMBORSO);
    }

    public void clickCaricaRimborso() {
        WebUtilities.clickElement(driver, CARICA_RIMBORSO);
    }

    public void clickBustaPagaCaricaTutte() {
        WebUtilities.clickElement(driver, BUSTA_PAGA_CARICA_TUTTE);
    }

    public void clickBustaPagaCaricaSingola() {
        WebUtilities.clickElement(driver, BUSTA_PAGA_CARICA_SINGOLA);
    }

    public void clickCertUnicaCaricaTutte() {
        WebUtilities.clickElement(driver, CERT_UNICA_CARICA_TUTTE);
    }

    public void clickCertUnicaCaricaSingola() {
        WebUtilities.clickElement(driver, CERT_UNICA_CARICA_SINGOLA);
    }

    public void clickFestivitaEChiusure() {
        WebUtilities.clickElement(driver, FESTIVITA_E_CHIUSURE);
    }

    public void clickConvenzioni() {
        WebUtilities.clickElement(driver, CONVENZIONI);
    }

    public void clickDocumenti() {
        WebUtilities.clickElement(driver, DOCUMENTI);
    }
}
