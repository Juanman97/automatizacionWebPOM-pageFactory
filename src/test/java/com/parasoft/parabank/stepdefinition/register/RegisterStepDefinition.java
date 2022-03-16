package com.parasoft.parabank.stepdefinition.register;

import com.parasoft.parabank.model.register.RegisterModel;
import com.parasoft.parabank.page.register.RegisterPage;
import com.parasoft.parabank.stepdefinition.setup.WebUI;
import com.parasoft.parabank.util.RandomUserGenerator;
import com.parasoft.parabank.util.Seconds;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class RegisterStepDefinition extends WebUI {
    private static Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    private RegisterPage registerPage;
    private RegisterModel registerModel;

    @Given("El usuario se encuentra en la pagina web de registro del banco")
    public void elUsuarioSeEncuentraEnLaPaginaWebDeRegistroDelBanco() {
        try {
            setUpLog4j();
            setUpWebDriver();
            generalSetUp();

            registerModel = new RegisterModel();
            registerModel.setFirstName("Juan Manuel");
            registerModel.setLastName("Reina");
            registerModel.setAddress("calle de prueba #20-22");
            registerModel.setCity("Neiva");
            registerModel.setState("Huila");
            registerModel.setZipCode("410010");
            registerModel.setPhone("12345678");
            registerModel.setSSN("99999999999");
            registerModel.setUsername(RandomUserGenerator.RandomUserGeneratorFunction());
            registerModel.setPassword("12345");
            registerModel.setWrongConfirmPassword("54321");
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @When("el usuario llena los campos obligatorios del formulario y confirma")
    public void elUsuarioLlenaLosCamposObligatoriosDelFormularioYConfirma() {
        try {
            registerPage = new RegisterPage(driver, Seconds.TEN.getValue(), registerModel);
            registerPage.goToRegister();
            registerPage.registerCorrectPassword();
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el sistema muestra un mensaje de bienvenida con el nombre completo del usuario y la confirmacion de creacion de la cuenta")
    public void elSistemaMuestraUnMensajeDeBienvenidaConElNombreCompletoDelUsuarioYLaConfirmacionDeCreacionDeLaCuenta() {
        Assertions.assertEquals(registerModel.getUsername(), registerPage.correctRegistrationNameResult());
        Assertions.assertTrue(registerPage.correctRegistrationMessageResult());
        quitDriver();
    }

    @When("el usuario llena los campos obligatorios del formulario con las contrasenias sin coincidir y confirma")
    public void elUsuarioLlenaLosCamposObligatoriosDelFormularioConLasContraseniasSinCoincidirYConfirma() {
        try {
            registerPage = new RegisterPage(driver, Seconds.TEN.getValue(), registerModel);
            registerPage.goToRegister();
            registerPage.registerIncorrectPassword();
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el sistema mostrara un mensaje de error")
    public void elSistemaMostraraUnMensajeDeError() {
        Assertions.assertTrue(registerPage.wrongPasswordResult());
        quitDriver();
    }
}
