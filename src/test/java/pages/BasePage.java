package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    private static WebDriverWait wait;

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Constructor vacío de BasePage.
     */
    public BasePage() {
        // Constructor vacío
    }

    /**
     * Navega a la URL indicada.
     * @param url Dirección web a la que se quiere navegar.
     */
    public static void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Busca y devuelve un elemento web ubicado por el locator dado.
     * @param locator Localizador del elemento.
     * @return WebElement encontrado.
     */
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Escribe un texto en un campo ubicado por el locator.
     * Limpia previamente el contenido del campo.
     * @param locator Localizador del campo.
     * @param textToWrite Texto a escribir.
     */
    public void write(By locator, String textToWrite) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(textToWrite);
    }

    /**
     * Hace clic en el elemento ubicado por el locator.
     * @param locator Localizador del elemento a clicar.
     */
    public void clickElement(By locator) {
        findElement(locator).click();
    }

    /**
     * Verifica si el elemento está visible en pantalla.
     * @param locator Localizador del elemento.
     * @return true si el elemento está visible, false si no existe o no es visible.
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Obtiene el texto del elemento ubicado por el locator.
     * @param locator Localizador del elemento.
     * @return Texto visible del elemento.
     */
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Cambia el contexto del driver a una alerta activa.
     * @return La alerta activa.
     */
    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    /**
     * Cierra el navegador y termina la sesión del WebDriver.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Cierra el navegador sin destruir el driver (opcional para reutilizar sesión).
     */
    public static void closeBrowser() {
        if (driver != null) {
            driver.close();
        }
    }
}
