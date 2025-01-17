package web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import urano.POM.CalendarioDipendenti;
import urano.POM.main.HomePage;
import urano.POM.main.SideNavbar;
import urano.POM.manager.WebPageManager;
import urano.config.WebConfig;
import urano.utilities.WebUtilities;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UranoTest {
        private static WebDriver driver;
        private static WebPageManager webPageManager;
        private static HomePage homePage;
        private static SideNavbar sideNavbar;
        private static CalendarioDipendenti calendarioDipendenti;

        @BeforeAll
        public static void setUp() {
            driver = WebConfig.createChromeDriver();
            driver.get("https://uranowebstage.azurewebsites.net/");
            webPageManager = new WebPageManager(driver);
            homePage = webPageManager.getHomePage();
            sideNavbar = webPageManager.getSideNavbar();
            calendarioDipendenti = webPageManager.getCalendarioDipendenti();

            String expectedUrl = "https://uranowebstage.azurewebsites.net/";
            assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Urano Home Page non mostrata.";
        }

        @Test
        public void testCheckHomepage() {
            String textToCheck = "Hello, world!";

            assert WebUtilities.isVisible(driver, homePage.getPageTitle()) : "Titolo Home Page non visibile.";
            assertTrue(homePage.getPageTitle().getText().contains(textToCheck), "Testo: " + textToCheck + ", non presente.");
        }

        @Test
        public void testGestioneReport() {
            String expectedUrl = "https://uranowebstage.azurewebsites.net/report/gestione";

            sideNavbar.clickGestioneReport();
            assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Gestione Report non visualizzata.";

        }

        @Test
        public void testCalendarioDipendenti() {
            String expectedUrl = "https://uranowebstage.azurewebsites.net/dipendenti/calendarioDipendenti";
            String expectedDetailUrl = "https://uranowebstage.azurewebsites.net/report/calendario/";
            String buName = "Azure";
            String month = "Ottobre";
            String year = "2024";

            sideNavbar.clickReportButton();
            sideNavbar.clickCalendarioDipendenti();
            assert WebUtilities.isUrlCorrect(driver, expectedUrl) : "Pagina Calendario Dipendenti non visualizzata.";
            calendarioDipendenti.clickBuFilterSwitchButton();
            calendarioDipendenti.clickBuArrow(buName);
            calendarioDipendenti.clickDropdownOptions();
            calendarioDipendenti.clickClearDropdownOption();
            calendarioDipendenti.selectYearsDropdownOption(year);
            calendarioDipendenti.selectMonthsDropdownOption(month);
            calendarioDipendenti.clickCercaButton();
            calendarioDipendenti.clickDettaglioButton();
            assertTrue(driver.getCurrentUrl().contains(expectedDetailUrl), "Pagina Dettaglio non visibile.");
        }
}
