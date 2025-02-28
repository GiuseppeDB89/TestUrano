package urano.POM.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

public class LoginPage {
    private WebDriver driver;

    private static final By EMAIL_BOX = By.name("loginfmt");
    private static final By PASSWORD_FIELD = By.name("passwd");
    private static final By AVANTI_BUTTON = By.className("button-item");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Email non valida: è nulla o vuota.");
            return;
        }
        try {
            WebElement emailBox = WebUtilities.findElement(driver, EMAIL_BOX);
            if (emailBox != null) {
                emailBox.clear();
                emailBox.sendKeys(email);
            } else {
                System.err.println("Campo email non trovato.");
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'email: " + e.getMessage());
        }
    }

    public void writePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.err.println("Password non valida: è nulla o vuota.");
            return;
        }
        try {
            WebElement passwordBox = WebUtilities.findElement(driver, PASSWORD_FIELD);
            if (passwordBox != null) {
                passwordBox.clear();
                passwordBox.sendKeys(password);
            } else {
                System.err.println("Campo password non trovato.");
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento della password: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            WebElement loginBtn = WebUtilities.findElement(driver, AVANTI_BUTTON);
            if (loginBtn != null) {
                loginBtn.click();
            } else {
                System.err.println("Pulsante Avanti / Accedi non trovato.");
            }
        } catch (Exception e) {
            System.err.println("Errore durante il click: " + e.getMessage());
        }
    }
}

