package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import pages.BasePage;

// Esta clase ejecuta los tests definidos en los archivos .feature usando Cucumber con JUnit
@RunWith(Cucumber.class)
@CucumberOptions(
    // Ruta donde están ubicados los archivos .feature
    features = "src/test/resources/features",

    // Paquete donde se encuentran los step definitions
    glue = "steps",

    // Plugins para generar reportes (ExtentReports y JSON)
    plugin = {
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "json:target/cucumber-reports.json"
    }
)
public class Runner {

    // Método que se ejecuta después de que todos los escenarios han terminado
    // Sirve para cerrar el navegador y liberar recursos
    @AfterClass
    public static void cleanDriver() {
        BasePage.closeBrowser();
    }
}
