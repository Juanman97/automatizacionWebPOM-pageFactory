package com.parasoft.parabank.stepdefinition.login;

import com.parasoft.parabank.model.login.LoginModel;
import com.parasoft.parabank.page.login.LoginPage;
import com.parasoft.parabank.stepdefinition.setup.WebUI;
import com.parasoft.parabank.util.Seconds;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class LogInStepDefinition extends WebUI {
    private static Logger LOGGER = Logger.getLogger(LogInStepDefinition.class);

    private LoginPage loginPage;
    private LoginModel loginModel;

    @Given("El usuario se encuentra en la pagina web del banco")
    public void elUsuarioSeEncuentraEnLaPaginaWebDelBanco() {
        try {
            setUpLog4j();
            setUpWebDriver();
            generalSetUp();

            loginModel = new LoginModel();
            loginModel.setUsername("Juanman97");
            loginModel.setPassword("12345");
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @When("el usuario ingresa sus credenciales de inicio de sesion y confirma")
    public void elUsuarioIngresaSusCredencialesDeInicioDeSesionYConfirma() {
        try {
            loginPage = new LoginPage(driver, Seconds.TEN.getValue(),loginModel);
            loginPage.successfulLogin();
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el sistema mostrara la pagina de cuenta de usuario con la informacion de cuentas, balances y monto disponible")
    public void elSistemaMostraraLaPaginaDeCuentaDeUsuarioConLaInformacionDeCuentasBalancesYMontoDisponible() {
        for (Boolean b : loginPage.successfulLoginResult()) {
            Assertions.assertTrue(b);
        }
        quitDriver();
    }
    @When("el usuario confirma el inicio de sesion con los campos de usuario y contrasenia vacios")
    public void elUsuarioConfirmaElInicioDeSesionConLosCamposDeUsuarioYContraseniaVacios() {
        try {
            loginPage = new LoginPage(driver, Seconds.TEN.getValue());
            loginPage.failedLogin();
        } catch (Exception e) {
            quitDriver();
            LOGGER.warn(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el sistema mostrara un mensaje de error")
    public void elSistemaMostraraUnMensajeDeError() {
        Assertions.assertTrue(loginPage.failedLoginResult());
        quitDriver();
    }
}
