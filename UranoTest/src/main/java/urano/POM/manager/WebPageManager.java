package urano.POM.manager;

import org.openqa.selenium.WebDriver;
import urano.POM.CalendarioDipendenti;
import urano.POM.main.HomePage;
import urano.POM.main.LoginPage;
import urano.POM.main.SideNavbar;

public class WebPageManager {
    private final WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SideNavbar sideNavbar;
    private CalendarioDipendenti calendarioDipendenti;

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

    public CalendarioDipendenti getCalendarioDipendenti() {
        if (calendarioDipendenti == null) {
            calendarioDipendenti = new CalendarioDipendenti(driver);
        }
        return calendarioDipendenti;
    }
}

