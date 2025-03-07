package urano.POM.manager;

import org.openqa.selenium.WebDriver;
import urano.POM.GestioneReportPage;
import urano.POM.cliente.DettaglioClientePage;
import urano.POM.cliente.GestioneClientePage;
import urano.POM.dipentente.*;
import urano.POM.main.HomePage;
import urano.POM.main.LoginPage;
import urano.POM.main.SideNavbar;
import urano.POM.ospite.CreaOspitePage;
import urano.POM.ospite.DettaglioOspitePage;
import urano.POM.ospite.GestioneOspitiPage;

public class WebPageManager {
    private final WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SideNavbar sideNavbar;
    private GestioneReportPage gestioneReportPage;
    private CalendarioDipendentiPage calendarioDipendenti;
    private DashboardDipendentePage dashboardDipendente;
    private GestioneOspitiPage gestioneOspitiPage;
    private CreaOspitePage creaOspitePage;
    private DettaglioOspitePage dettaglioOspitePage;
    private RiepilogoAttivitaDipendentePage riepilogoAttivitaDipendentePage;
    private ResocontoMensilePage resocontoMensilePage;
    private RiepilogoTimbraturePage riepilogoTimbraturePage;
    private CalendarioDipendenteSpecifico calendarioDipendenteSpecifico;
    private GestioneDipendentePage gestioneDipendentePage;
    private CreaDipendentePage creaDipendetePage;
    private AssegnaTechLeadPage assegnaTechLeadPage;
    private DettaglioTechLeadPage dettaglioTechLeadPage;
    private GestioneClientePage gestioneClientePage;
    private DettaglioClientePage dettaglioClientePage;

    public WebPageManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public SideNavbar getSideNavbar() {
        if (sideNavbar == null) {
            sideNavbar = new SideNavbar(driver);
        }
        return sideNavbar;
    }

    public GestioneReportPage getGestioneReportPage() {
        if (gestioneReportPage == null) {
            gestioneReportPage = new GestioneReportPage(driver);
        }
        return gestioneReportPage;
    }

    public CalendarioDipendentiPage getCalendarioDipendenti() {
        if (calendarioDipendenti == null) {
            calendarioDipendenti = new CalendarioDipendentiPage(driver);
        }
        return calendarioDipendenti;
    }

    public DashboardDipendentePage getDashboardDipendente() {
        if (dashboardDipendente == null) {
            dashboardDipendente = new DashboardDipendentePage(driver);
        }
        return dashboardDipendente;
    }

    public GestioneOspitiPage getGestioneOspitiPage() {
        if (gestioneOspitiPage == null) {
            gestioneOspitiPage = new GestioneOspitiPage(driver);
        }
        return gestioneOspitiPage;
    }

    public CreaOspitePage getCreaOspitePage() {
        if (creaOspitePage == null) {
            creaOspitePage = new CreaOspitePage(driver);
        }
        return creaOspitePage;
    }

    public DettaglioOspitePage getDettaglioOspitePage() {
        if (dettaglioOspitePage == null) {
            dettaglioOspitePage = new DettaglioOspitePage(driver);
        }
        return dettaglioOspitePage;
    }

    public RiepilogoAttivitaDipendentePage getRiepilogoAttivitaDipendentePage() {
        if (riepilogoAttivitaDipendentePage == null) {
            riepilogoAttivitaDipendentePage = new RiepilogoAttivitaDipendentePage(driver);
        }
        return riepilogoAttivitaDipendentePage;
    }

    public ResocontoMensilePage getResocontoMensilePage() {
        if (resocontoMensilePage == null) {
            resocontoMensilePage = new ResocontoMensilePage(driver);
        }
        return resocontoMensilePage;
    }

    public RiepilogoTimbraturePage getRiepilogoTimbraturePage() {
        if (riepilogoTimbraturePage == null) {
            riepilogoTimbraturePage = new RiepilogoTimbraturePage(driver);
        }
        return riepilogoTimbraturePage;
    }

    public CalendarioDipendenteSpecifico getCalendarioDipendenteSpecifico() {
        if (calendarioDipendenteSpecifico == null) {
            calendarioDipendenteSpecifico = new CalendarioDipendenteSpecifico(driver);
        }
        return calendarioDipendenteSpecifico;
    }

    public GestioneDipendentePage getGestioneDipendentePage() {
        if (gestioneDipendentePage == null) {
            gestioneDipendentePage = new GestioneDipendentePage(driver);
        }
        return gestioneDipendentePage;
    }

    public CreaDipendentePage getCreaDipendetePage() {
        if (creaDipendetePage == null) {
            creaDipendetePage = new CreaDipendentePage(driver);
        }
        return creaDipendetePage;
    }

    public AssegnaTechLeadPage getAssegnaTechLeadPage() {
        if (assegnaTechLeadPage == null) {
            assegnaTechLeadPage = new AssegnaTechLeadPage(driver);
        }
        return assegnaTechLeadPage;
    }

    public DettaglioTechLeadPage getDettaglioTechLeadPage() {
        if (dettaglioTechLeadPage == null) {
            dettaglioTechLeadPage = new DettaglioTechLeadPage(driver);
        }
        return dettaglioTechLeadPage;
    }

    public GestioneClientePage getGestioneClientePage() {
        if (gestioneClientePage == null) {
            gestioneClientePage = new GestioneClientePage(driver);
        }
        return gestioneClientePage;
    }

    public DettaglioClientePage getDettaglioClientePage() {
        if (dettaglioClientePage == null) {
            dettaglioClientePage = new DettaglioClientePage(driver);
        }
        return dettaglioClientePage;
    }
}

