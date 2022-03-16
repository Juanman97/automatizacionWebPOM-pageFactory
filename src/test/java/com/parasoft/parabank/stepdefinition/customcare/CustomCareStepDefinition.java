package com.parasoft.parabank.stepdefinition.customcare;

import com.parasoft.parabank.model.customcare.CustomCareModel;
import com.parasoft.parabank.page.customcare.CustomCarePage;
import com.parasoft.parabank.stepdefinition.setup.WebUI;
import com.parasoft.parabank.util.Seconds;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class CustomCareStepDefinition extends WebUI {
    private static final Logger LOGGER = Logger.getLogger(CustomCareStepDefinition.class);

    private CustomCareModel customCareModel;
    private CustomCarePage customCarePage;

    @Given("el usuario se encuentra en la pagina web de Contact Us")
    public void elUsuarioSeEncuentraEnLaPaginaWebDeContactUs() {
        try {
            setUpLog4j();
            setUpWebDriver();
            generalSetUp();

            customCareModel = new CustomCareModel();
            customCareModel.setName("Juan Manuel Reina");
            customCareModel.setEmail("juanmanuel@gmail.com");
            customCareModel.setPhone("123456789");
            customCareModel.setMessage("Este es un mensaje de prueba");
        } catch (Exception e) {
            quitDriver();
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @When("el usuario ingresa todos los campos del formulario y confirma")
    public void elUsuarioIngresaTodosLosCamposDelFormularioYConfirma() {
        try {
            customCarePage = new CustomCarePage(driver, Seconds.TEN.getValue(), customCareModel);
            customCarePage.goToCustomerCare();
            customCarePage.sendFilledForm();
        } catch (Exception e) {
            quitDriver();
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @Then("el sistema mostrara en pantalla un mensaje de contacto exitoso")
    public void elSistemaMostraraEnPantallaUnMensajeDeContactoExitoso() {
        Assertions.assertEquals(customCareModel.getName(), customCarePage.filledFormSubmitResult());
        quitDriver();
    }
    @When("el usuario no ingresa ningun campo del formulario y confirma")
    public void elUsuarioNoIngresaNingunCampoDelFormularioYConfirma() {
        try {
            customCarePage = new CustomCarePage(driver, Seconds.TEN.getValue(), customCareModel);
            customCarePage.goToCustomerCare();
            customCarePage.sendEmptyForm();
        } catch (Exception e) {
            quitDriver();
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @Then("el sistema no enviara el mensaje y mostrara los campos requeridos")
    public void elSistemaNoEnviaraElMensajeYMostraraLosCamposRequeridos() {
        Assertions.assertTrue(customCarePage.emptyFormSubmitResult());
        quitDriver();
    }
}
