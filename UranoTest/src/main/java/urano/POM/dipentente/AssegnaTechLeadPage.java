package urano.POM.dipentente;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import urano.utilities.WebUtilities;

import java.util.List;

public class AssegnaTechLeadPage {
    private WebDriver driver;

    private static final By TECH_LEAD_GRID = By.className("rz-data-grid");
    private static final By BUTTONS = By.className("rz-button");
    private static final By TECH_LEAD_FORM = By.className("rz-dialog");
    private static final By DROPDOWN = By.className("rz-dropdown");
    private static final By BODY = By.className("rz-body");

    public AssegnaTechLeadPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTechLeadGrid() {
        return WebUtilities.findElement(driver, TECH_LEAD_GRID);
    }

    public WebElement getTechLeadForm() {
        return WebUtilities.findElement(driver, TECH_LEAD_FORM);
    }

    public WebElement getBodyPage() {
        return WebUtilities.findElement(driver, BODY);
    }

    public void clickAggiungiTechLead() {
        WebUtilities.clickButtonByText(driver, BUTTONS, "Aggiungi Tech Lead");
    }

    public void clickSalvaButton() {
        WebUtilities.clickButtonByText(driver, BUTTONS, "Salva");
    }

    public void clickGoBackButton() {
        List<WebElement> stacks = WebUtilities.findElements(driver, By.className("rz-stack"));
        for(WebElement stack : stacks) {
            try {
                WebElement titleElement = stack.findElement(By.tagName("h4"));
                if (titleElement.getText().contains("Assegna Tech Lead")) {
                    stack.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }

    public void clickTechLeadDetailsButton(String teachLeadName) {
        List<WebElement> rows = WebUtilities.findElements(driver, By.cssSelector(".rz-data-row"));
        for(WebElement row : rows) {
            try {
                String name = row.findElement(By.tagName("p")).getText();
                if(WebUtilities.isNameEqual(name, teachLeadName)) {
                    row.findElement(By.className("rz-button")).click();
                    break;
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
    }

    public void selectDipendenteDaAggiungere(String nomeDipendente) {
        WebUtilities.clickElement(driver, DROPDOWN);
        WebElement input = WebUtilities.findElement(driver, By.className("rz-dropdown-filter"));
        input.sendKeys(nomeDipendente);

        List<WebElement> options = WebUtilities.findElements(driver, By.className("rz-dropdown-item"));
        for (WebElement option : options) {
            if (option.getText().contains(nomeDipendente)) {
                option.click();
                break;
            }
        }
    }
}
