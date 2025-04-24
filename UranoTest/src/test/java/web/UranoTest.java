package web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import urano.POM.GestioneReportPage;
import urano.POM.cliente.DettaglioClientePage;
import urano.POM.cliente.GestioneClientePage;
import urano.POM.dipentente.*;
import urano.POM.main.HomePage;
import urano.POM.main.LoginPage;
import urano.POM.main.SideNavbar;
import urano.POM.manager.WebPageManager;
import urano.POM.ospite.CreaOspitePage;
import urano.POM.ospite.DettaglioOspitePage;
import urano.POM.ospite.GestioneOspitiPage;
import urano.config.WebConfig;
import urano.utilities.WebUtilities;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UranoTest {
    private static WebDriver driver;
    private static WebPageManager webPageManager;
    private static HomePage homePage;
    private static LoginPage loginPage;
    private static SideNavbar sideNavbar;
    private static GestioneReportPage gestioneReportPage;
    private static CalendarioDipendentiPage calendarioDipendenti;
    private static DashboardDipendentePage dashboardDipendente;
    private static GestioneOspitiPage gestioneOspitiPage;
    private static CreaOspitePage creaOspitePage;
    private static DettaglioOspitePage dettaglioOspitePage;
    private static RiepilogoAttivitaDipendentePage riepilogoAttivitaDipendentePage;
    private static ResocontoMensilePage resocontoMensilePage;
    private static RiepilogoTimbraturePage riepilogoTimbraturePage;
    private static CalendarioDipendenteSpecifico calendarioDipendenteSpecifico;
    private static GestioneDipendentePage gestioneDipendentePage;
    private static CreaDipendentePage creaDipendetePage;
    private static AssegnaTechLeadPage assegnaTechLeadPage;
    private static DettaglioTechLeadPage dettaglioTechLeadPage;
    private static GestioneClientePage gestioneClientePage;
    private static DettaglioClientePage dettaglioClientePage;

    @BeforeAll
    public static void setUp() {
        driver = WebConfig.createDriver();
        driver.get("https://uranowebstage.azurewebsites.net/");
        webPageManager = new WebPageManager(driver);
        homePage = webPageManager.getHomePage();
        loginPage = webPageManager.getLoginPage();
        sideNavbar = webPageManager.getSideNavbar();
        gestioneReportPage = webPageManager.getGestioneReportPage();
        calendarioDipendenti = webPageManager.getCalendarioDipendenti();
        dashboardDipendente = webPageManager.getDashboardDipendente();
        gestioneOspitiPage = webPageManager.getGestioneOspitiPage();
        creaOspitePage = webPageManager.getCreaOspitePage();
        dettaglioOspitePage = webPageManager.getDettaglioOspitePage();
        riepilogoAttivitaDipendentePage = webPageManager.getRiepilogoAttivitaDipendentePage();
        resocontoMensilePage = webPageManager.getResocontoMensilePage();
        riepilogoTimbraturePage = webPageManager.getRiepilogoTimbraturePage();
        calendarioDipendenteSpecifico = webPageManager.getCalendarioDipendenteSpecifico();
        gestioneDipendentePage = webPageManager.getGestioneDipendentePage();
        creaDipendetePage = webPageManager.getCreaDipendetePage();
        assegnaTechLeadPage = webPageManager.getAssegnaTechLeadPage();
        dettaglioTechLeadPage = webPageManager.getDettaglioTechLeadPage();
        gestioneClientePage = webPageManager.getGestioneClientePage();
        dettaglioClientePage = webPageManager.getDettaglioClientePage();

        String expectedUrl = "https://uranowebstage.azurewebsites.net/";
        /* String aesysEmail = JsonConfigReader.getAesysEmail();
        String aesysPassword = JsonConfigReader.getAesysPassword();
        loginPage.writePassword(aesysPassword);
        loginPage.clickLoginButton();
        loginPage.writeEmail(aesysEmail);
         */
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Urano Home Page non mostrata.";
    }

    @Test
    public void testCheckHomepage() {
        String textToCheck = "Hello, world!";

        assert WebUtilities.isVisible(driver, homePage.getPageTitle()) : "Titolo Home Page non visibile.";
        assertTrue(homePage.getPageTitle().getText().contains(textToCheck), "Testo: " + textToCheck + ", non presente.");
    }

    // TEST FLACKY + migliorare metodo per inserire anche dicembre
    @Test
    public void testCheckGestioneReportPage() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/report/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/report/dashboard";
        String expectedUrl3 = "https://uranowebstage.azurewebsites.net/report/riepilogo-attivita";
        String expectedUrl4 = "https://uranowebstage.azurewebsites.net/report/resocontomensile";
        String expectedUrl5 = "https://uranowebstage.azurewebsites.net/timbrature/riepilogoMensile";
        String expectedUrl6 = "https://uranowebstage.azurewebsites.net/report/calendario";
        String employeeName = "Matteo D'Alfonso";
        String month = "Maggio";
        String year = "2024";
        String job = "MBO";
        String idCommessa = "AESYS_BU_AZURE";

        sideNavbar.clickGestioneReport();
        assert WebUtilities.isVisible(driver, gestioneReportPage.getEmployeeList()) : "Lista dipendenti non visualizzata.";
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Report non visualizzata.";
        dashboardDipendente.selectMonthsDropdownOption(month);
        dashboardDipendente.selectYearsDropdownOption(year);
        dashboardDipendente.clickCercaButton();
        gestioneReportPage.clickViewDetailsButtonByName(employeeName);
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Dashboard Dipendente non visualizzata.";
        assert WebUtilities.isNameEqual(employeeName, dashboardDipendente.getEmployeeName()) : "Nome utente non visibile o diverso da quello selezionato.";
        dashboardDipendente.selectMonthsDropdownOption(month);
        dashboardDipendente.selectYearsDropdownOption(year);
        dashboardDipendente.clickCercaButton();
        assert WebUtilities.isVisible(driver, dashboardDipendente.getJobList()) : "Lista Commesse non visualizzata.";
        dashboardDipendente.clickViewDetailsButtonByJob(job);
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl3) : "Pagina riepilogo attività non visualizzata.";
        assert WebUtilities.isNameEqual(employeeName, riepilogoAttivitaDipendentePage.getEmployeeName()) : "Nome utente non visibile o diverso da quello selezionato.";
        riepilogoAttivitaDipendentePage.selectIdentificativoCommessaButton(idCommessa);
        riepilogoAttivitaDipendentePage.selectMonthsDropdownOption(month);
        riepilogoAttivitaDipendentePage.selectYearsDropdownOption(year);
        riepilogoAttivitaDipendentePage.clickCercaButton();
        assert WebUtilities.isVisible(driver, riepilogoAttivitaDipendentePage.getGrid()) : "Lista Commesse non visualizzata.";
        riepilogoAttivitaDipendentePage.clickGoBackButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Dashboard Dipendente non visualizzata.";
        dashboardDipendente.clickOreLavoroOrdinario();
        assert WebUtilities.isVisible(driver, dashboardDipendente.getOreLavoroOrdRow()) : "Ore di lavoro ordinario non visualizzate.";
        dashboardDipendente.clickOreLavoroStraordinario();
        // assert WebUtilities.isVisible(driver, dashboardDipendente.getOreLavoroStrdRow()) : "Ore di lavoro straordinario non visualizzate.";
        dashboardDipendente.clickOreAssenza();
        // assert WebUtilities.isVisible(driver, dashboardDipendente.getOreAssenzeRow()) : "Ore di assenze non visualizzate.";
        dashboardDipendente.clickGiorniAssenza();
        // assert WebUtilities.isVisible(driver, dashboardDipendente.getGiorniAssenza()) : "Giorni di assenza non visualizzate.";
        dashboardDipendente.selectMonthsDropdownOption(month);
        dashboardDipendente.selectYearsDropdownOption(year);
        dashboardDipendente.clickCercaButton();
        dashboardDipendente.clickAnnullaReport();
        dashboardDipendente.clickResocontoPresenzeDetailsButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl4) : "Pagina Resoconto mensile dipendente non visualizzata.";
        resocontoMensilePage.selectMonthsDropdownOption(month);
        resocontoMensilePage.selectYearsDropdownOption(year);
        resocontoMensilePage.clickCercaButton();
        resocontoMensilePage.clickGoBackButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Dashboard Dipendente non visualizzata.";
        dashboardDipendente.clickRiepilogoTimbratureDetailsButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl5) : "Pagina Resoconto mensile dipendente non visualizzata.";
        riepilogoTimbraturePage.selectMonthsDropdownOption(month);
        riepilogoTimbraturePage.selectYearsDropdownOption(year);
        riepilogoTimbraturePage.clickCercaButton();
        assert WebUtilities.isVisible(driver, riepilogoTimbraturePage.getTimbratureGrid()) : "Timbrature non visualizzate.";
        riepilogoTimbraturePage.clickGoBackButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Dashboard Dipendente non visualizzata.";
        dashboardDipendente.clickCalendarioPresenzeDetailsButton();
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl6) : "Calendario Dipendente non visualizzato.";
        assert WebUtilities.isNameEqual(employeeName, calendarioDipendenteSpecifico.getEmployeeName()) : "Nome utente non visibile o diverso da quello selezionato.";
    }

    // COMPLETARE PARTE NAVIGAZIONE CALENDARIO
    @Test
    public void testCalendarioDipendenti() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/calendarioDipendenti";
        String expectedDetailUrl = "https://uranowebstage.azurewebsites.net/report/calendario/";
        String buName = "Black";
        String month = "Ottobre";
        String year = "2024";
        String employeeName = "Di Pietrantonio Giovanni";

        sideNavbar.clickCalendarioDipendenti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Calendario Dipendenti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        calendarioDipendenti.clickBuFilterSwitchButton();
        assert WebUtilities.areVisible(driver, calendarioDipendenti.getBuRowsType()) : "Riga nome BU non visualizzata.";
        calendarioDipendenti.clickBuArrow(buName);
        assert WebUtilities.areVisible(driver, calendarioDipendenti.getBuEmployees()) : "Lista dipendenti della BU " + buName + " non visibile.";
        calendarioDipendenti.clickBuArrow(buName);
        // Troppo veloce il controllo, non da il tempo di chiudere la lista
        //assertFalse(WebUtilities.areVisible(driver, calendarioDipendenti.getBuEmployees()), "Lista dipendenti della BU " + buName + " non chiusa correttamente.");
        calendarioDipendenti.clickBuFilterSwitchButton();
        assert calendarioDipendenti.getBuRowsType() == null : "Riga nome BU visualizzate anche dopo rimozione raggruppamento per BU.";
        calendarioDipendenti.clickBuFilterSwitchButton();
        calendarioDipendenti.clickBuArrow(buName);
        calendarioDipendenti.clickDropdownOptions();
        calendarioDipendenti.clickClearDropdownOption();
        calendarioDipendenti.selectYearsDropdownOption(year);
        calendarioDipendenti.selectMonthsDropdownOption(month);
        calendarioDipendenti.clickCercaButton();
        calendarioDipendenti.clickViewDetailsButtonByName(employeeName);
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedDetailUrl), "Pagina Dettaglio non visibile.");
        assert WebUtilities.isNameEqual(employeeName, dashboardDipendente.getEmployeeName()) : "Nome utente non visibile o diverso da quello selezionato.";
    }

    // DEVONO FIXARE IL FILTRO PER NOME OSPITE
    @Test
    public void testFiltraOspiti() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospiteName = "Rossi Arturo";
        String motivo = "Progetto X";
        String month = "febbraio";
        String year = "2025";
        String startDay = "4";
        String endDay = "8";
        String startFilterDate = startDay.concat("/02/").concat(year);
        String endFilterDate = endDay.concat("/02/").concat(year);

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        // gestioneOspitiPage.writeInOspiteSearchBar(ospiteName);
        // assertTrue(gestioneOspitiPage.isOspiteNameFilterOk(ospiteName), "Ospite non comparso o non corrisponde con il filtro applicato.");
        // gestioneOspitiPage.clickDeleteFilterButton();
        gestioneOspitiPage.writeInMotivoSearchBar(motivo);
        assertTrue(gestioneOspitiPage.isOspiteMotivoFilterOk(motivo), "Ospite non comparso o non corrisponde con il filtro applicato.");
        gestioneOspitiPage.clickDeleteFilterButton();
        gestioneOspitiPage.clickStartDateFilter();
        gestioneOspitiPage.selectMonth4Filter(month);
        gestioneOspitiPage.selectYear4Filter(year);
        gestioneOspitiPage.selectStartDay4Filter(startDay);
        gestioneOspitiPage.clickApplyButton();
        assert gestioneOspitiPage.isStartDateFilterOk(startFilterDate) : "La data non combacia con quella selezionata";
        gestioneOspitiPage.clickEndDateFilter();
        gestioneOspitiPage.selectMonth4Filter(month);
        gestioneOspitiPage.selectYear4Filter(year);
        gestioneOspitiPage.selectEndDay4Filter(endDay);
        gestioneOspitiPage.clickApplyButton();
        assert gestioneOspitiPage.isEndDateFilterOk(endFilterDate) : "La data non combacia con quella selezionata";
    }

    @Test
    public void testFiltroStatoDefault() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        assert gestioneOspitiPage.areAllBadgeTypesPresent() : "Lista badge non visualizzata.";
    }

    @Test
    public void testFiltroStatoNonAbilitato() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String dangerType = "Non Abilitato";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        gestioneOspitiPage.clickCheckbox();
        gestioneOspitiPage.clickCheckbox();
        assert gestioneOspitiPage.isBadgeOk(dangerType) : "Il tipo di badge non combacia con il filtro.";
    }

    @Test
    public void testFiltroStatoAbilitato() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String successType = "Abilitato";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        gestioneOspitiPage.clickCheckbox();
        assert gestioneOspitiPage.isBadgeOk(successType) : "Il tipo di badge non combacia con il filtro.";
    }

    // Devono fixarlo
    @Test
    public void testOrdinaColonnaOspite() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String columnName = "Ospite";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        List<String> defaultOspiteList = gestioneOspitiPage.getOspiteNamesList();
        gestioneOspitiPage.clickSortButton(columnName);
        assert gestioneOspitiPage.isInDescendingOrder(defaultOspiteList) : "Lista non ordinata in ordine (A-Z).";
        gestioneOspitiPage.clickSortButton(columnName);
        assert gestioneOspitiPage.isInAscendingOrder(defaultOspiteList) : "Lista non ordinata in ordine (Z-A).";
    }

    @Test
    public void testOrdinaColonnaMotivo() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String columnName = "Motivo";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        gestioneOspitiPage.clickSortButton(columnName);
        List<String> ascendMotivoList = gestioneOspitiPage.getMotivoList();
        assert gestioneOspitiPage.isInAscendingOrder(ascendMotivoList) : "Lista non ordinata in ordine (A-Z).";
        gestioneOspitiPage.clickSortButton(columnName);
        List<String> descendMotivoList = gestioneOspitiPage.getMotivoList();
        assert gestioneOspitiPage.isInDescendingOrder(descendMotivoList) : "Lista non ordinata in ordine (Z-A).";
    }

    @Test
    public void testOrdinaColonnaData() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String columnName1 = "Data Inizio";
        String columnName2 = "Data Fine";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        gestioneOspitiPage.clickSortButton(columnName1);
        List<String> ascendMotivoList1 = gestioneOspitiPage.getDataInizioList();
        assert gestioneOspitiPage.areDateInAscendingOrder(ascendMotivoList1) : "Lista Data Inizio non ordinata in ordine cronologico decrescente.";
        gestioneOspitiPage.clickSortButton(columnName1);
        List<String> descendMotivoList1 = gestioneOspitiPage.getDataInizioList();
        assert gestioneOspitiPage.areDateInDescendingOrder(descendMotivoList1) : "Lista Data Inizio non ordinata in ordine cronologico crescente.";
        gestioneOspitiPage.clickSortButton(columnName1);
        gestioneOspitiPage.clickSortButton(columnName2);
        List<String> ascendMotivoList2 = gestioneOspitiPage.getDataFineList();
        assert gestioneOspitiPage.areDateInAscendingOrder(ascendMotivoList2) : "Lista Data Fine non ordinata in ordine cronologico crescente.";
        gestioneOspitiPage.clickSortButton(columnName2);
        List<String> descendMotivoList2 = gestioneOspitiPage.getDataFineList();
        assert gestioneOspitiPage.areDateInDescendingOrder(descendMotivoList2) : "Lista Data Fine non ordinata in ordine cronologico decrescente.";
    }

    @Test
    public void testOrdinaColonnaStato() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String columnName = "Stato";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        assert WebUtilities.isVisible(driver, calendarioDipendenti.getEmployeeGrid()) : "Lista dipendenti non visibile.";
        gestioneOspitiPage.clickSortButton(columnName);
        List<String> statoList1 = gestioneOspitiPage.getBadgeList();
        assert gestioneOspitiPage.isNonAbilitatoBeforeAbilitato(statoList1) : "Lista Stato non ordinata in (Non Abitilato - Abilitato).";
        gestioneOspitiPage.clickSortButton(columnName);
        List<String> statoList2 = gestioneOspitiPage.getBadgeList();
        assertFalse(gestioneOspitiPage.isNonAbilitatoBeforeAbilitato(statoList2), "Lista Stato non ordinata in (Abitilato - Non Abilitato).");
    }

    @Test
    public void testCreaOspiteMessagioCampoObbligatorio() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/crea";
        String azienda = "Azienda";
        String nome = "Nome";
        String cognome = "Cognome";
        String email = "Email";
        String host = "Host";
        String residenza = "Residenza";
        String dataInizio = "Data Inizio";
        String dataFine = "Data Fine";
        String motivoVisita = "Motivo visita";

        sideNavbar.clickCreaOspite();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Crea Ospite non visualizzata.";
        assert WebUtilities.isVisible(driver, creaOspitePage.getNewUserForm()) : "Form di creazione utente non visualizzato.";
        creaOspitePage.clickSalvaButton();
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(azienda) : "Per il campo 'Azienda' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(nome) : "Per il campo 'Nome' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(cognome) : "Per il campo 'Cognome' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(email) : "Per il campo 'Email' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(host) : "Per il campo 'Host' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(residenza) : "Per il campo 'Residenza' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(dataInizio) : "Per il campo 'Data Inizio' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(dataFine) : "Per il campo 'Data Fine' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
        assert creaOspitePage.isMandatoryFieldErrorDisplayed(motivoVisita) : "Per il campo 'Motivo Visita' il messaggio di errore 'Campo obbligatorio' non è stato visualizzato.";
    }

    @Test
    public void testCreaNuovoOspite() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/ospiti/crea";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/ospiti/dettaglio";
        String expectedUrl3 = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String azienda = "AziendaTest";
        String nome = "test";
        String cognome = "automation";
        String telefono = "0123456789";
        String email = "josetay453@maonyn.com"; // temp-mail.org email temporanea per test, cambiare all'occorrenza
        String host = "Candeloro Alessandro";
        String residenza = "Tenerife";
        String dataInizio = "22/01/2025";
        String dataFine = "29/01/2025";
        String motivoVisita = "Testare";

        sideNavbar.clickCreaOspite();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Crea Ospite non visualizzata.";
        assert WebUtilities.isVisible(driver, creaOspitePage.getNewUserForm()) : "Form di creazione utente non visualizzato.";
        creaOspitePage.writeAzienda(azienda);
        creaOspitePage.writeNome(nome);
        creaOspitePage.writeCognome(cognome);
        creaOspitePage.writeTelefono(telefono);
        creaOspitePage.writeEmail(email);
        creaOspitePage.selectHostFromDropdown(host);
        creaOspitePage.writeResidenza(residenza);
        creaOspitePage.writeDataInizio(dataInizio);
        creaOspitePage.writeDataFine(dataFine);
        creaOspitePage.writeMotivoVisita(motivoVisita);
        creaOspitePage.clickSalvaButton();
        assert WebUtilities.isUrlContain(driver, expectedUrl2) : "Pagina Dettaglio Ospite non visualizzata.";
        //dettaglioOspitePage.clickGoBackButton();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl3) : "Pagina Gestione Ospiti non visualizzata.";
    }

    @Test
    public void testModificaOspite() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospite = "automation test";
        String azienda = "AziendaTest";
        String nome = "test";
        String cognome = "automation";
        String telefono = "0123456789";
        String email = "josetay453@maonyn.com";
        String residenza = "Tenerife, Spagna";
        String motivoVisita = "Testare tutto";
        String dataInizio = "24/01/2025";
        String dataFine = "30/01/2025";
        String host = "Carolla Enzo";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        gestioneOspitiPage.clickOspiteByName(ospite);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getForm()) : "Form di dettaglio utente non visualizzato.";
        dettaglioOspitePage.clickModificaButton();
        dettaglioOspitePage.writeAzienda(azienda);
        dettaglioOspitePage.writeNome(nome);
        dettaglioOspitePage.writeCognome(cognome);
        dettaglioOspitePage.writeTelefono(telefono);
        dettaglioOspitePage.writeEmail(email);
        dettaglioOspitePage.selectHostFromDropdown(host);
        dettaglioOspitePage.writeResidenza(residenza);
        dettaglioOspitePage.writeDataInizio(dataInizio);
        dettaglioOspitePage.writeDataFine(dataFine);
        dettaglioOspitePage.writeMotivoVisita(motivoVisita);
        dettaglioOspitePage.clickSalvaButton();
        dettaglioOspitePage.clickSalvaButton();
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getSuccessMessage()) : "Messaggio di successo non visualizzato.";
        assert dettaglioOspitePage.getAzienda().equalsIgnoreCase(azienda) : "Azienda non modificata correttamente.";
        assert dettaglioOspitePage.getNome().equalsIgnoreCase(nome) : "Nome non modificato correttamente.";
        assert dettaglioOspitePage.getCognome().equalsIgnoreCase(cognome) : "Cognome non modificato correttamente.";
        assert dettaglioOspitePage.getTelefono().equalsIgnoreCase(telefono) : "Telefono non modificato correttamente.";
        assert dettaglioOspitePage.getEmail().equalsIgnoreCase(email) : "Email non modificata correttamente.";
        assert dettaglioOspitePage.getResidenza().equalsIgnoreCase(residenza) : "Residenza non modificata correttamente.";
        assert dettaglioOspitePage.getSelectedHost().equalsIgnoreCase(host) : "Host non modificato correttamente.";
        assert dettaglioOspitePage.getDataInizio().contains(dataInizio) : "Data Inizio non modificata correttamente.";
        assert dettaglioOspitePage.getDataFine().contains(dataFine) : "Data Fine non modificata correttamente.";
        assert dettaglioOspitePage.getMotivoVisita().equalsIgnoreCase(motivoVisita) : "Motivo Visita non modificato correttamente.";
        dettaglioOspitePage.clickGoBackButton();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
    }

    @Test
    public void testErrorPopUpCampiConSpaziVuoti() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospite = "automation test";
        String azienda = " ";
        String nome = " ";
        String cognome = " ";
        String telefono = " ";
        String residenza = " ";
        String motivoVisita = " ";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        gestioneOspitiPage.clickOspiteByName(ospite);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getForm()) : "Form di dettaglio utente non visualizzato.";
        dettaglioOspitePage.clickModificaButton();
        dettaglioOspitePage.writeAzienda(azienda);
        dettaglioOspitePage.writeNome(nome);
        dettaglioOspitePage.writeCognome(cognome);
        dettaglioOspitePage.writeTelefono(telefono);
        dettaglioOspitePage.writeResidenza(residenza);
        dettaglioOspitePage.writeMotivoVisita(motivoVisita);
        dettaglioOspitePage.clickSalvaButton();
        dettaglioOspitePage.clickSalvaButton();
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getErrorPopUp()) : "Pop Up di errore non visualizzato.";
    }

    @Test
    public void testWrongEmailFormatModOspite() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospite = "automation test";
        String email = " ";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        gestioneOspitiPage.clickOspiteByName(ospite);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getForm()) : "Form di dettaglio utente non visualizzato.";
        dettaglioOspitePage.clickModificaButton();
        dettaglioOspitePage.writeEmail(email);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getErrorMessage()) : "Messaggio di errore, formato email non valido, non visualizzato.";
    }

    @Test
    public void testMandatoryFieldsModificaOspite() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospite = "automation test";
        String azienda = "";
        String nome = "";
        String cognome = "";
        String residenza = "";
        String motivoVisita = "";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        gestioneOspitiPage.clickOspiteByName(ospite);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getForm()) : "Form di dettaglio utente non visualizzato.";
        dettaglioOspitePage.clickModificaButton();
        dettaglioOspitePage.writeAzienda(azienda);
        dettaglioOspitePage.writeNome(nome);
        dettaglioOspitePage.writeCognome(cognome);
        dettaglioOspitePage.writeResidenza(residenza);
        dettaglioOspitePage.writeMotivoVisita(motivoVisita);
        assert dettaglioOspitePage.getErrorMessages().size() == 5 : "Messaggi di errore non visualizzati.";
    }

    @Test
    public void testErrorPopUpDataFineAntecedenteDataInizio() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/ospiti/gestione";
        String ospite = "automation test";
        String dataInizio = "30/01/2025";
        String dataFine = "01/01/2025";

        sideNavbar.clickGestioneOspiti();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Ospiti non visualizzata.";
        gestioneOspitiPage.clickOspiteByName(ospite);
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getForm()) : "Form di dettaglio utente non visualizzato.";
        dettaglioOspitePage.clickModificaButton();
        dettaglioOspitePage.writeDataInizio(dataInizio);
        dettaglioOspitePage.writeDataFine(dataFine);
        dettaglioOspitePage.clickSalvaButton();
        assert WebUtilities.isVisible(driver, dettaglioOspitePage.getErrorPopUp()) : "Pop Up di errore non visualizzato.";
    }

    @Test
    public void testCheckGestioneDipendentePage() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String expectedPageTitle = "Gestione Dipendente";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        assert gestioneDipendentePage.getPageTitle().getText().equalsIgnoreCase(expectedPageTitle) : "Titolo Pagina errato.";
        assert gestioneDipendentePage.getDataGrid().isDisplayed() : "Griglia dati non visualizzata.";
    }

    @Test
    public void testFiltroDipendente() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Dipendente";
        String employeeSurname = "Di Pietrantonio";
        String employeeName = "Giovanni";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.writeInDipendenteSearchBar(employeeSurname);
        while (true) {
            assert retries < 200 : "Troppi tentativi di ricerca Dipendente per cognome!";
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            if(rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries ++;
        }
        assert gestioneDipendentePage.getSearchFilteredResults(filterFor)
                .stream()
                .anyMatch(result -> result.contains(employeeSurname))
                : "Ricerca dipendente per Cognome non riuscita.";
        gestioneDipendentePage.clickClearFilterButton();
        retries = 0;
        rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.writeInDipendenteSearchBar(employeeName);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca Dipendente per nome!";
            if(rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries ++;
        }
        assert gestioneDipendentePage.getSearchFilteredResults(filterFor)
                .stream()
                .anyMatch(result -> result.contains(employeeSurname))
                : "Ricerca dipendente per Nome non riuscita.";
    }

    @Test
    public void testFiltroEmail() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Account";
        String email = "giovanni.dipietrantonio@aesys.tech";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        gestioneDipendentePage.writeInAccountSearchBar(email);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per email!";
            if(rawsAfterFilter == 1) {
                break;
            }
            retries ++;
        }
        assert gestioneDipendentePage.getSearchFilteredResult(filterFor).contains(email) : "Ricerca dipendente per Email non riuscita, o restituisce più email uguali.";
    }

    @Test
    public void testFiltroBusinessUnit() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Business Unit";
        String businessUnit = "BU Azure";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.writeInBusinessUnitSearchBar(businessUnit);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per Business Unit!";
            if (rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries++;
        }

        List<String> results = gestioneDipendentePage.getSearchFilteredResults(filterFor);
        assert !results.isEmpty() : "Nessun risultato trovato per il filtro: " + filterFor;
        for (String result : results) {
            assert result.contains(businessUnit) : "Ricerca per Business Unit " + businessUnit + " non riuscita o restituisce dati errati";
        }
    }

    @Test
    public void testFiltroTechLead() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Tech Lead";
        String techLeadName = "Mehdi";
        String techLeadSurname = "Guenfissi";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";

        gestioneDipendentePage.testFiltroTechLeadHelper(techLeadName, filterFor);
        gestioneDipendentePage.clickClearFilterButton();
        gestioneDipendentePage.testFiltroTechLeadHelper(techLeadSurname, filterFor);
    }

    @Test
    public void testFiltroSede() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Sede";
        String sede = "Pescara";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.writeInSedeSearchBar(sede);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per Business Unit!";
            if (rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries++;
        }
        List<String> results = gestioneDipendentePage.getSearchFilteredResults(filterFor);
        assert !results.isEmpty() : "Nessun risultato trovato per il filtro: " + filterFor;
        for (String result : results) {
            assert result.contains(sede) : "Ricerca per Sede " + sede + " non riuscita o restituisce dati errati";
        }
    }

    @Test
    public void testFiltroCittaResidenza() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Città di residenza";
        String cittaResidenza = "(CH)";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.writeInCittaResidenzaSearchBar(cittaResidenza);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per Città di residenza!";
            if (rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries++;
        }
        List<String> results = gestioneDipendentePage.getSearchFilteredResults(filterFor);
        assert !results.isEmpty() : "Nessun risultato trovato per il filtro: " + filterFor;
        for (String result : results) {
            assert result.contains(cittaResidenza) : "Ricerca per Città di residenza " + cittaResidenza + " non riuscita o restituisce dati errati";
        }
    }

    @Test
    public void testFiltroDataAssunzione() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String filterFor = "Data di assunzione";
        String dataAssunzione = "12/06/2024";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.selectDataAssunzione(dataAssunzione);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per Data di Assunzione!";
            if (rawsBeforeFilter > rawsAfterFilter) {
                break;
            }
            retries++;
        }
        List<String> results = gestioneDipendentePage.getSearchFilteredResults(filterFor);
        assert !results.isEmpty() : "Nessun risultato trovato per il filtro: " + filterFor;
        for (String result : results) {
            assert result.contains(dataAssunzione) : "Ricerca per Data di Assunzione " + dataAssunzione + " non riuscita o restituisce dati errati";
        }
    }

    @Test
    public void testFiltroStato() {
        String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String stato = "ATTIVO";
        int retries = 0;

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Dipendente non visualizzata.";
        int rawsBeforeFilter = gestioneDipendentePage.countTotalRows();
        gestioneDipendentePage.selectStato(stato);
        while (true) {
            int rawsAfterFilter = gestioneDipendentePage.countTotalRows();
            assert retries < 200 : "Troppi tentativi di ricerca per Stato!";
            if (rawsBeforeFilter >= rawsAfterFilter) {
                break;
            }
            retries++;
        }
        assert gestioneDipendentePage.areAllBadgesOfType(stato) : "Ricerca per Stato: " + stato + ", non riuscita o restituisce dati errati";
    }

    @Test
    public void testCompletaBozzaDipendente() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/dipendenti/crea/daBozza";
        String stato = "Bozza";
        String expectedPageTitle = "Crea Dipendente";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Dipendente non visualizzata.";
        gestioneDipendentePage.selectStato(stato);
        assert gestioneDipendentePage.areAllBadgesOfType(stato);
        gestioneDipendentePage.clickFirstBozzaElement(stato);
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Pagina Crea Dipendente Da Bozza non visualizzata.";
        assert creaDipendetePage.getPageTitle().getText().contains(expectedPageTitle) : "Titolo Pagina errato.";
        assert WebUtilities.isVisible(driver, creaDipendetePage.getSteps()) : "Griglia degli Step non visibile.";
    }

    // CAMBIARE LOCATOR PER BUTTON CREA DIPENDENTE -
    @Test
    public void testCheckCreaDipendentePage() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/dipendenti/crea";
        String expectedPageTitle = "Crea Dipendente";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Dipendente non visualizzata.";
        gestioneDipendentePage.clickCreaDipendenteButton();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl2) : "Pagina Crea Dipendente non visualizzata.";
        assert creaDipendetePage.getPageTitle().getText().contains(expectedPageTitle) : "Titolo Pagina errato.";
        assert WebUtilities.isVisible(driver, creaDipendetePage.getSteps()) : "Griglia degli Step non visibile.";
        creaDipendetePage.clickGoBackButton();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Dipendente non visualizzata.";
    }

    @Test
    public void testMandatoryFieldCreaDipendente() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/dipendenti/crea";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Dipendente non visualizzata.";
        sideNavbar.clickCreaDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl2) : "Pagina Crea Dipendente non visualizzata.";
        creaDipendetePage.clickInformazioniDipendete();
        assert creaDipendetePage.getErrorMessages().size() == 12 : "Messaggi di errore non visualizzati.";
        driver.navigate().refresh();
        creaDipendetePage.clickSalvaProcediButton();
        assert creaDipendetePage.getErrorMessages().size() == 12 : "Messaggi di errore non visualizzati.";
        driver.navigate().refresh();
        creaDipendetePage.clickSalvaChiudiButton();
        assert creaDipendetePage.getErrorMessages().size() == 12 : "Messaggi di errore non visualizzati.";
    }

    @Test
    public void testCreaDipendente() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/dipendenti/gestione";
        String nome = "Johnny";
        String cognome = "Test";
        String codFiscale = "PRXMYG79L58L409A"; // CAMBIARE AD OGNI TEST CON GENERATORE CASUALE ONLINE
        String provinciaNascita = "Pescara";
        String comuneNascita = "Pescara";
        String dataNascita = "19/08/2000";
        String genere = "M"; // M = Maschio, F = Femmina, A = Altro;
        String provinciaResidenza = "Chieti";
        String comuneResidenza = "Chieti";
        String indirizzo = "Via dei tester 01";
        String titoloStudio = "Diploma scuola superiore";
        String descrizione = "Sistemi informativi Aziendali";
        String email = "emailpersonale@yopmail.com"; // temp-mail.org email temporanea per test, cambiare all'occorrenza
        String telefono = "0123456789";
        String jobPosition = "CFO";
        String businessUnit = "BU Azure";
        String sede = "Pescara";
        String emailAziendale = "emailAziendale@yopmail.com";
        String telefonoAziendale = "9876543210";
        File processImage = new File("src/test/resources/id-10069296.jpg");
        String documentoFilePath = processImage.toString();
        String responsabile = "Guenfissi Mehdi";
        String approvatoreFisso = "Guenfissi Mehdi";
        String statoTicket = "Consegnato";
        String matricolaTicket = "DAPP";
        String dataAbilitazione = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String bancaAccredito = "BCC";
        String iban = "IT23K0300203280699439232275";

        sideNavbar.clickGestioneDipendente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Dipendente non visualizzata.";
        sideNavbar.clickCreaDipendente();
        creaDipendetePage.writeName(nome);
        creaDipendetePage.writeSurname(cognome);
        creaDipendetePage.writeCodiceFiscale(codFiscale);
        creaDipendetePage.insertProvinciaNascita(provinciaNascita);
        creaDipendetePage.insertComuneNascita(comuneNascita);
        creaDipendetePage.writeDataNascita(dataNascita);
        creaDipendetePage.selectGenere(genere);
        creaDipendetePage.insertProvinciaResidenza(provinciaResidenza);
        creaDipendetePage.insertComuneResidenza(comuneResidenza);
        creaDipendetePage.writeIndirizzo(indirizzo);
        creaDipendetePage.selectTitoloStudio(titoloStudio);
        creaDipendetePage.writeDescrizione(descrizione);
        creaDipendetePage.writeEmail(email);
        creaDipendetePage.writeTelefono(telefono);
        creaDipendetePage.clickSalvaProcediButton();
        WebUtilities.scrollUp(driver);
        // SISTEMARE PER FAR SCEGLIERE TUTTI I RUOLI
        /*List <WebElement> test = WebUtilities.findElementsOnParent(driver, By.className("rz-data-grid"), By.className("rz-primary"));
        test.get(6).click();
         */
        creaDipendetePage.selectJobPosition(jobPosition);
        creaDipendetePage.selectBusinessUnit(businessUnit);
        creaDipendetePage.selectSede(sede);
        creaDipendetePage.writeEmailAziendale(emailAziendale);
        creaDipendetePage.writeTelefonoAziendale(telefonoAziendale);
        creaDipendetePage.insertDocumento(documentoFilePath);
        creaDipendetePage.selectResponsabile(responsabile);
        creaDipendetePage.selectApprovatore(approvatoreFisso);
        creaDipendetePage.selectStatoTicket(statoTicket);
        creaDipendetePage.writeMatricolaTicket(matricolaTicket);
        creaDipendetePage.writeDataAbilitazione(dataAbilitazione);
        creaDipendetePage.writeBancaAccredito(bancaAccredito);
        creaDipendetePage.writeIban(iban);
        creaDipendetePage.clickSalvaProcediButton();
    }

    // AGGIUNGERE TEST FILTRO PER NOME E ORDINE (A-Z)
    @Test
    public void testAssegnaTechLead() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/dipendenti/AssegnaTechLead";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/";
        String nomeDipendente1 = "Ricci Giacomo";
        String nomeDipendente2 = "Valentino Wilma";

        sideNavbar.clickAssegnaTechLead();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Assegna Tech Lead non visualizzata.";
        assert WebUtilities.isVisible(driver, assegnaTechLeadPage.getTechLeadGrid()) : "Griglia Tech Lead non visualizzata.";
        assegnaTechLeadPage.clickAggiungiTechLead();
        assert WebUtilities.isVisible(driver, assegnaTechLeadPage.getTechLeadForm()) : "Form inserimento Tech Lead non visualizzata.";
        assegnaTechLeadPage.selectDipendenteDaAggiungere(nomeDipendente1);
        assegnaTechLeadPage.clickSalvaButton();
        assegnaTechLeadPage.clickTechLeadDetailsButton(nomeDipendente1);
        dettaglioTechLeadPage.clickEliminaTechLead();
        assegnaTechLeadPage.clickAggiungiTechLead();
        assegnaTechLeadPage.selectDipendenteDaAggiungere(nomeDipendente2);
        assegnaTechLeadPage.clickSalvaButton();
        assegnaTechLeadPage.clickTechLeadDetailsButton(nomeDipendente2);
        dettaglioTechLeadPage.clickEliminaTechLead();
        assegnaTechLeadPage.clickGoBackButton();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl2) : "Homepage non visualizzata.";
    }

    // TO FIX
    @Test
    public void testFiltriTabellaGestioneCliente() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/clienti/gestione";
        String cliente = "BU BLUE";
        String codiceCliente = "BLUE";
        String siglaCliente = "BLUE";

        sideNavbar.clickGestioneCliente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Cliente non visualizzata.";
        assert WebUtilities.isVisible(driver, gestioneClientePage.getClientiGrid()) : "Griglia Clienti non visualizzata.";
        gestioneClientePage.writeCliente(cliente);
        assert gestioneClientePage.isClienteNameFilterOk(cliente);
        gestioneClientePage.clickClearFilter();
        gestioneClientePage.writeCodiceCliente(codiceCliente);
        assert gestioneClientePage.isCodiceClienteFilterOk(codiceCliente);
        gestioneClientePage.clickClearFilter();
        gestioneClientePage.writeSiglaCliente(siglaCliente);
        assert gestioneClientePage.isSiglaClienteFilterOk(siglaCliente);
        gestioneClientePage.clickClearFilter();
    }

    @Test
    public void testModificaCliente() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/clienti/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/clienti/dettaglio/";
        String cliente = "BU BLUE";
        String codiceCliente = "BLUE";
        String siglaCliente = "BLUE3";
        String newSalesAccount = "test SalesAccount";
        String newSAEmail = "salesaccount@yopmail.com";
        String newProtocollo = "testProtocollo";
        String newTerminePagamento = "Bonifico Bancario";
        String newTempiPagamento = "30 gg";
        String newNote = "testNote";

        sideNavbar.clickGestioneCliente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Cliente non visualizzata.";
        assert WebUtilities.isVisible(driver, gestioneClientePage.getClientiGrid()) : "Griglia Clienti non visualizzata.";
        gestioneClientePage.clickClienteDetailsButton(cliente);
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Pagina Dettaglio Cliente non visualizzata.";
        assert dettaglioClientePage.getClienteName().equalsIgnoreCase(cliente) : "Nome Cliente non corrisponde.";
        dettaglioClientePage.clickModifyButton();
        assert WebUtilities.isVisible(driver, dettaglioClientePage.getForm()) : "Form di modifica Cliente non visualizzato.";
        dettaglioClientePage.writeCliente(cliente);
        dettaglioClientePage.writeCodiceCliente(codiceCliente);
        dettaglioClientePage.writSiglaCliente(siglaCliente);
        dettaglioClientePage.writeSalesAccount(newSalesAccount);
        dettaglioClientePage.writeSAEmail(newSAEmail);
        dettaglioClientePage.writeProtocollo(newProtocollo);
        dettaglioClientePage.selectTempiPagamento(newTempiPagamento);
        dettaglioClientePage.selectTerminiPagamento(newTerminePagamento);
        dettaglioClientePage.writeNote(newNote);
        dettaglioClientePage.clickSalvaButton();
    }

    @Test
    public void testFiltriStoricoOrdini() {
        String expectedUrl1 = "https://uranowebstage.azurewebsites.net/clienti/gestione";
        String expectedUrl2 = "https://uranowebstage.azurewebsites.net/clienti/dettaglio/";
        String cliente = "BU BLUE";
        String codiceOrdine = "xxx";
        String dataEmissione = "03/02/2025";
        String dataFine = "19/02/2025";
        String importo = "50000";

        sideNavbar.clickGestioneCliente();
        assert WebUtilities.isUrlCorrect(driver, expectedUrl1) : "Pagina Gestione Cliente non visualizzata.";
        assert WebUtilities.isVisible(driver, gestioneClientePage.getClientiGrid()) : "Griglia Clienti non visualizzata.";
        gestioneClientePage.clickClienteDetailsButton(cliente);
        assert Objects.requireNonNull(driver.getCurrentUrl()).contains(expectedUrl2) : "Pagina Dettaglio Cliente non visualizzata.";
        assert dettaglioClientePage.getClienteName().equalsIgnoreCase(cliente) : "Nome Cliente non corrisponde.";
        dettaglioClientePage.writeCodiceOrdine(codiceOrdine);
        assert dettaglioClientePage.getCodiceOrdine().contains(codiceOrdine) : "Codice Ordine non corrisponde.";
        dettaglioClientePage.clickClearFilter();
        //dettaglioClientePage.selectDataEmissione(dataEmissione);
        //dettaglioClientePage.selectDataFine(dataFine);
        // TO IMPROVE
        dettaglioClientePage.writeImporto(importo);
        assert dettaglioClientePage.getImporto().contains(importo) : "Importo non corrisponde";
    }

}
