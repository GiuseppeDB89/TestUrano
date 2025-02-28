package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import urano.utilities.WebUtilities;

public class CalendarioDipendenteSpecifico {
    private WebDriver driver;

    private static final By EMPLOYEE_NAME = By.cssSelector("h5.rz-text-h5");

    public CalendarioDipendenteSpecifico(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmployeeName() {
        return WebUtilities.findElement(driver, EMPLOYEE_NAME).getText();
    }
}
