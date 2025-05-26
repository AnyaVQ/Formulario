package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import pages.BasePage;
import pages.FormularioContactoPage;

public class FormularioContactoSteps {

    private FormularioContactoPage formularioPage = new FormularioContactoPage();

    /**
     * Navega a la página del formulario de contacto y maneja alerta si existe.
     */
    @Given("El usuario está en la página del formulario de contacto")
    public void el_usuario_esta_en_la_pagina_del_formulario_de_contacto() {
        BasePage.navigateTo("http://64.227.54.255/Softesting/Frontend/Caso1.html");
        manejarAlertaSiPresente();
    }

    /**
     * Ingresa texto en el campo especificado del formulario.
     */
    @When("El usuario ingresa {string} en el campo {string}")
    public void el_usuario_ingresa_en_el_campo(String texto, String campo) {
        switch (campo) {
            case "Tu Nombre:":
                formularioPage.escribirNombre(texto);
                break;
            case "Tu Email:":
                formularioPage.escribirEmail(texto);
                break;
            case "Varrio:":
                formularioPage.escribirVarrio(texto);
                break;
            case "Asunto:":
                formularioPage.escribirAsunto(texto);
                break;
            case "Mensage:":
                formularioPage.escribirMensaje(texto);
                break;
        }
    }

    /**
     * Deja vacío el campo especificado.
     */
    @When("El usuario deja vacío el campo {string}")
    public void el_usuario_deja_vacio_el_campo(String campo) {
        el_usuario_ingresa_en_el_campo("", campo);
    }

    /**
     * Hace clic en el botón indicado y maneja alerta si aparece.
     */
    @When("El usuario hace clic en el botón {string}")
    public void el_usuario_hace_clic_en_el_boton(String textoBoton) {
        if (textoBoton.equals("Enviar")) {
            formularioPage.clickEnviar();
            manejarAlertaSiPresente();
        } else if (textoBoton.equals("Caso 2")) {
            formularioPage.clickCaso2();
            manejarAlertaSiPresente();
        }
    }

    /**
     * Verifica que se muestre un mensaje de error específico.
     */
    @Then("Se debe mostrar un mensaje de error indicando que {string}")
    public void se_debe_mostrar_un_mensaje_de_error_indicando_que(String mensajeError) {
        if (mensajeError.contains("Nombre")) {
            Assert.assertTrue(formularioPage.isMensajeErrorNombreVisible());
        } else if (mensajeError.contains("Email")) {
            Assert.assertTrue(formularioPage.isMensajeErrorEmailFormatoVisible());
        } else if (mensajeError.contains("Varrio")) {
            Assert.assertTrue(formularioPage.isMensajeErrorVarrioVisible());
        } else if (mensajeError.contains("Asunto")) {
            Assert.assertTrue(formularioPage.isMensajeErrorAsuntoVisible());
        } else {
            Assert.fail("Mensaje de error no reconocido: " + mensajeError);
        }
    }

    /**
     * Verifica que se muestre la página de error indicada.
     */
    @Then("Se debe mostrar la página de error {string}")
    public void se_debe_mostrar_la_pagina_de_error(String textoError) {
        Assert.assertTrue(BasePage.driver.getTitle().contains(textoError) 
                          || BasePage.driver.getPageSource().contains(textoError));
        manejarAlertaSiPresente();
    }

    /**
     * Verifica que se muestre una alerta con el mensaje esperado y la acepta.
     */
    @Then("Se debe mostrar una alerta con el mensaje {string}")
    public void se_debe_mostrar_una_alerta_con_el_mensaje(String mensajeAlerta) {
        Assert.assertEquals(mensajeAlerta, formularioPage.getAlertaTexto());
        formularioPage.aceptarAlerta();
    }

    /**
     * Verifica que el formulario no fue enviado (no cambia URL a éxito).
     */
    @Then("El formulario no debe ser enviado")
    public void el_formulario_no_debe_ser_enviado() {
        manejarAlertaSiPresente();
        Assert.assertFalse(BasePage.driver.getCurrentUrl().contains("exito"));
    }

    /**
     * Intenta detectar y aceptar una alerta si está presente en la página.
     */
    private void manejarAlertaSiPresente() {
        try {
            Alert alert = BasePage.driver.switchTo().alert();
            System.out.println("Alerta encontrada: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No hay alerta presente
        }
    }
}
