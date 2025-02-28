package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class DettaglioTechLeadPage {
    private WebDriver driver;

    private static final By ELIMINA_TECHLEAD_BUTTON = By.cssSelector(".rz-card .rz-button");

    public DettaglioTechLeadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEliminaTechLead() {
        WebUtilities.clickElement(driver, ELIMINA_TECHLEAD_BUTTON);
    }

    public void clickGoBackButton() {
        List<WebElement> stacks = WebUtilities.findElements(driver, By.className("rz-stack"));
        for(WebElement stack : stacks) {
            try {
                WebElement titleElement = stack.findElement(By.tagName("h4"));
                if (titleElement.getText().contains("Dettaglio Tech Lead")) {
                    stack.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }
}
