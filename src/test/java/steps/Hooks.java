package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import pages.BasePage;

/**
 * Clase que contiene los "hooks" (ganchos) de Cucumber.
 * Los hooks son métodos que se ejecutan antes o después de cada escenario o paso.
 * Esta clase hereda de BasePage para tener acceso a la instancia del WebDriver.
 */
public class Hooks extends BasePage {

    /**
     * Constructor de la clase Hooks.
     * Llama al constructor de la clase padre (BasePage) pasando la instancia del WebDriver.
     */
    public Hooks() {
        super();
    }

    /**
     * Método que se ejecuta después de cada escenario de Cucumber (@After).
     * Se utiliza para realizar acciones de limpieza o reporte después de la ejecución de cada escenario.
     * @param scenario El objeto Scenario de Cucumber que representa el escenario que acaba de ejecutarse.
     */
    @After
    public void tearDown(Scenario scenario) {
        // Verifica si el escenario falló.
        if (scenario.isFailed()) {
            // Si el escenario falló, añade un mensaje al reporte de Cucumber.
            scenario.log("El escenario falló, por favor, consulte la imagen adjunta a este reporte");

            // Toma una captura de pantalla del estado actual del navegador como un array de bytes.
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Adjunta la captura de pantalla al reporte de Cucumber.
            // El primer argumento es el contenido de la captura de pantalla (en bytes).
            // El segundo argumento es el tipo de medio (MIME type) de la captura de pantalla.
            // El tercer argumento es el nombre que se le dará a la captura de pantalla en el reporte.
            scenario.attach(screenshot, "image/png", "Captura de pantalla del error");
        }
    }
}