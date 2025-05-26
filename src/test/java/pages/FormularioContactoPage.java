package pages;

import org.openqa.selenium.By;

public class FormularioContactoPage extends BasePage {

    // Locators (¡VERIFICA ESTOS 'name' Y OTROS ATRIBUTOS REALES!)
    private By nombreInput = By.id("nombre");
    private By emailInput = By.name("email");
    private By varrioInput = By.name("barrio"); // ¡Con la 'b' minúscula!
    private By asuntoInput = By.name("asunto");
    private By mensajeTextarea = By.name("Mensaje");
    private By enviarBoton = By.xpath("//button[normalize-space()='Enviar']");
    private By caso2Boton = By.xpath("//a[normalize-space()='Caso 2']"); // ¡VERIFICA EL LOCATOR REAL!

    // Mensajes de error (¡VERIFICA LOS TEXTOS REALES!)
    private By mensajeErrorNombre = By.xpath("//p[contains(text(),'El nombre debe ser mayor a 4 letras y no debe incl')]");
    private By mensajeErrorEmailFormato = By.xpath("//p[contains(text(),'El Email debe ser mayor a 4 caracteres y debe llev')]");
    private By mensajeErrorVarrio = By.xpath("//div[@id='grupo__barrio']//p[@class='formulario__input-error formulario__input-error-activo'][normalize-space()='El nombre debe ser mayor a 4 letras']"); // Asumiendo mismo mensaje
    private By mensajeErrorAsunto = By.xpath("//div[@id='grupo__asunto']//p[@class='formulario__input-error formulario__input-error-activo'][normalize-space()='El nombre debe ser mayor a 4 letras']"); // Asumiendo mismo mensaje

    public FormularioContactoPage() {
    }

    public void escribirNombre(String nombre) {
        write(nombreInput, nombre);
    }

    public void escribirEmail(String email) {
        write(emailInput, email);
    }

    public void escribirVarrio(String varrio) {
        write(varrioInput, varrio);
    }

    public void escribirAsunto(String asunto) {
        write(asuntoInput, asunto);
    }

    public void escribirMensaje(String mensaje) {
        write(mensajeTextarea, mensaje);
    }

    public void clickEnviar() {
        clickElement(enviarBoton);
    }

    public void clickCaso2() {
        clickElement(caso2Boton);
    }

    public boolean isMensajeErrorNombreVisible() {
        return isElementDisplayed(mensajeErrorNombre);
    }

    public boolean isMensajeErrorEmailFormatoVisible() {
        return isElementDisplayed(mensajeErrorEmailFormato);
    }

    public boolean isMensajeErrorVarrioVisible() {
        return isElementDisplayed(mensajeErrorVarrio);
    }

    public boolean isMensajeErrorAsuntoVisible() {
        return isElementDisplayed(mensajeErrorAsunto);
    }

    public String getAlertaTexto() {
        return switchToAlert().getText();
    }

    public void aceptarAlerta() {
        switchToAlert().accept();
    }
}