package urano.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtilities {
    public static FluentWait<WebDriver> createFluentWait(WebDriver driver, long timeoutInSeconds) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public static boolean isVisible(WebDriver driver, WebElement elementToCheck) {
        try {
            FluentWait<WebDriver> wait = createFluentWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(elementToCheck));
            return true;
        } catch (TimeoutException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;  // Non possiamo recuperare l'elemento se è stale
        }
    }

    public static boolean areVisible(WebDriver driver, List<WebElement> elementsToCheck) {
        try {
            FluentWait<WebDriver> wait = createFluentWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfAllElements(elementsToCheck));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUrlCorrect(WebDriver driver, String url) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 5);
        try {
            wait.until(ExpectedConditions.urlToBe(url));
            return true;
        } catch (Exception e) {
            System.err.println("URL non corretta: " + e.getMessage());
            return false;
        }
    }

    public static boolean isUrlContain(WebDriver driver, String urlToContain) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 10);
        try {
            wait.until(ExpectedConditions.urlContains(urlToContain));
            return true;
        } catch (Exception e) {
            System.err.println("URL non corretta: " + e.getMessage());
            return false;
        }
    }

    public static By getLocatorFromElement(WebDriver driver, WebElement element) {
        String className = element.getAttribute("class");
        if (className != null && !className.isEmpty()) {
            return By.className(className.split(" ")[0]); // Prende solo la prima classe
        }

        throw new IllegalArgumentException("Impossibile determinare il By da questo elemento");
    }

    public static void clickElement(WebDriver driver, By elementToClick) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
    }

    public static void clickWebElement(WebDriver driver, WebElement elementToClick) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
    }

    public static WebElement findElement(WebDriver driver, By elementoToFind) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementoToFind));
    }

    public static List<WebElement> findElements(WebDriver driver, By by) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static WebElement findElementOnParent(WebDriver driver, WebElement parent, By by) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, by));
    }

    public static List<WebElement> findElementsOnParent(WebDriver driver, By parent, By by) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parent, by));
    }

    public static void waitElementLoad(WebDriver driver, WebElement byToWait) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(byToWait));
    }

    public static void waitForUrl(WebDriver driver, String expectedUrl) {
        FluentWait<WebDriver> wait = createFluentWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public static void typeWordLetterByLetter(WebDriver driver, String word) {
        Actions actions = new Actions(driver);
        for (char letter : word.toCharArray()) {
            actions.sendKeys(String.valueOf(letter)).perform();
        }
    }

    public static void typeWord(WebDriver driver, String word) {
        Actions actions = new Actions(driver);
        actions.sendKeys(word).perform();
    }

    public static void clickButtonByText(WebDriver driver, By buttonBy, String buttonText) {
        List<WebElement> buttons = WebUtilities.findElements(driver, buttonBy);
        for(WebElement button : buttons) {
            if(button.getText().contains(buttonText)) {
                button.click();
                return;
            }
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void scrollUp(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    public static void scrollDownContinuously(WebDriver driver) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < 2; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            try {
                Thread.sleep(500); // Pausa tra gli scroll per garantire il caricamento dei contenuti
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void hoverWithMouse(WebDriver driver, WebElement elementToDelete) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(elementToDelete).perform();
        } catch (Exception e) {
            System.err.println("Errore durante il hover sull'elemento: " + e.getMessage());
            throw e;
        }
    }

    public static boolean isNameEqual(String name1, String name2) {
        // Divide le stringhe in liste di parole
        List<String> parts1 = Arrays.asList(name1.trim().split("\\s+"));
        List<String> parts2 = Arrays.asList(name2.trim().split("\\s+"));

        // Converte entrambe le liste in insiemi (Set) per ignorare l'ordine
        Set<String> set1 = new HashSet<>(parts1);
        Set<String> set2 = new HashSet<>(parts2);

        // Confronta i due insiemi
        return set1.equals(set2);
    }

    public static boolean containsNameInAnyOrder(String fullText, String nameToCheck) {
        String[] partsToCheck = nameToCheck.trim().split("\\s+");
        if (partsToCheck.length != 6) {
            return false;
        }
        String originalOrder = partsToCheck[0] + " " + partsToCheck[1];
        String reversedOrder = partsToCheck[1] + " " + partsToCheck[0];

        return fullText.toLowerCase().contains(originalOrder.toLowerCase()) ||
                fullText.toLowerCase().contains(reversedOrder.toLowerCase());
    }

    public static String getOTPFromEmail(String email, By emailBox, By otpLocator) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\GiovanniDiPietranton\\AppData\\Local\\Google\\Chrome\\User Data\\NewProfile");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*"); // Evita errori legati a CORS o altri blocchi
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        // Memorizza l'handle della finestra originale
        String originalWindow = driver.getWindowHandle();
        // Apri una nuova finestra
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        try {
            newWindow.get("https://yopmail.com/it/");
            WebElement emailInput = WebUtilities.findElement(newWindow, emailBox);
            emailInput.clear();
            emailInput.sendKeys(email);
            WebUtilities.clickElement(newWindow, By.cssSelector("#refreshbut .md"));

            WebElement otpElement = WebUtilities.findElement(newWindow, otpLocator);
            String otpText = otpElement.getText();

            // Usa una regex per estrarre solo i numeri
            Pattern pattern = Pattern.compile("\\d+"); // Cerca una o più cifre
            Matcher matcher = pattern.matcher(otpText);

            String otp = null;
            if (matcher.find()) {
                otp = matcher.group(); // Ottieni il primo gruppo corrispondente (i numeri)
                System.out.println("OTP recuperato: " + otp);
            } else {
                throw new RuntimeException("Non è stato possibile estrarre l'OTP dal testo: " + otpText);
            }

            newWindow.close();
            driver.switchTo().window(originalWindow);
            return otp;
        } catch (Exception e) {
            newWindow.close();
            driver.switchTo().window(originalWindow);
            throw new RuntimeException("Errore durante il recupero dell'OTP: " + e.getMessage(), e);
        }
    }

    /*
    public static String getOTPcodeFromYopmail(WebDriver driver, String email, By emailBox, By lastEmails, By otpCode) {
        driver.get("https://yopmail.com/it/");
        WebElement emailInput = findElement(driver, emailBox);
        emailInput.sendKeys(email);
        List<WebElement> emails = findElements(driver, lastEmails);
        emails.getFirst().click();
        WebElement otp = findElement(driver, otpCode);
        System.out.println(otp.getText());
        return otp.getText();
    }
     */
}