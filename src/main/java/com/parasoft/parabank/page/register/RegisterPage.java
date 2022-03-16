package com.parasoft.parabank.page.register;

import com.parasoft.parabank.model.customcare.CustomCareModel;
import com.parasoft.parabank.model.register.RegisterModel;
import com.parasoft.parabank.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends CommonActionOnPages {
    private static Logger LOGGER = Logger.getLogger(RegisterPage.class);
    private RegisterModel registerModel;

    //Localizadores para navegación
    @CacheLookup
    @FindBy(linkText = "Register")
    private WebElement register;

    //Localizadores para input de datos
    @CacheLookup
    @FindBy(id = "customer.firstName")
    private WebElement firstName;

    @CacheLookup
    @FindBy(id = "customer.lastName")
    private WebElement lastName;

    @CacheLookup
    @FindBy(id = "customer.address.street")
    private WebElement address;

    @CacheLookup
    @FindBy(id = "customer.address.city")
    private WebElement city;

    @CacheLookup
    @FindBy(id = "customer.address.state")
    private WebElement state;

    @CacheLookup
    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCode;

    @CacheLookup
    @FindBy(id = "customer.phoneNumber")
    private WebElement phone;

    @CacheLookup
    @FindBy(id = "customer.ssn")
    private WebElement ssn;

    @CacheLookup
    @FindBy(id = "customer.username")
    private WebElement username;

    @CacheLookup
    @FindBy(id = "customer.password")
    private WebElement password;

    @CacheLookup
    @FindBy(id = "repeatedPassword")
    private WebElement repeatedPassword;

    @CacheLookup
    @FindBy(xpath = "//input[@type = 'submit' and @value = 'Register']")
    private WebElement submit;

    //Localizadores para assertions
    @CacheLookup
    @FindBy(id = "repeatedPassword.errors")
    private WebElement assertionPasswordError;

    @CacheLookup
    @FindBy(xpath = "//h1[contains(text(), 'Welcome')]")
    private WebElement assertionWelcomeMessage;

    @CacheLookup
    @FindBy(xpath = "//p[contains(text(), 'successfully')]")
    private WebElement assertionSuccessfulRegistration;

    //constructor
    public RegisterPage(WebDriver driver, int seconds, RegisterModel registerModel) {
        super(driver, seconds);
        pageFactoryInitElement(driver, this);
        this.registerModel = registerModel;
    }

    //funcionalidades
    public void goToRegister() {
        scroll(register);
        click(register);
    }

    private void fillRegisterFormMandatoryFields() {
        scroll(firstName);
        clear(firstName);
        typeOn(firstName, registerModel.getFirstName());

        scroll(lastName);
        clear(lastName);
        typeOn(lastName, registerModel.getLastName());

        scroll(address);
        clear(address);
        typeOn(address, registerModel.getAddress());

        scroll(city);
        clear(city);
        typeOn(city, registerModel.getCity());

        scroll(state);
        clear(state);
        typeOn(state, registerModel.getState());

        scroll(zipCode);
        clear(zipCode);
        typeOn(zipCode, registerModel.getZipCode());

        scroll(ssn);
        clear(ssn);
        typeOn(ssn, registerModel.getSSN());

        scroll(username);
        clear(username);
        typeOn(username, registerModel.getUsername());

        scroll(password);
        clear(password);
        typeOn(password, registerModel.getPassword());
    }

    public void registerCorrectPassword() {
        fillRegisterFormMandatoryFields();

        scroll(repeatedPassword);
        clear(repeatedPassword);
        typeOn(repeatedPassword, registerModel.getPassword());

        scroll(submit);
        submit(submit);
    }

    public void registerIncorrectPassword() {
        fillRegisterFormMandatoryFields();

        scroll(repeatedPassword);
        clear(repeatedPassword);
        typeOn(repeatedPassword, registerModel.getWrongConfirmPassword());

        scroll(submit);
        submit(submit);
    }

    public boolean wrongPasswordResult() {
        return isDisplayed(assertionPasswordError);
    }

    public String correctRegistrationNameResult() {
        String result;
        result = getText(assertionWelcomeMessage).trim();
        result = result.replace("Welcome ", "");
        return result;
    }

    public boolean correctRegistrationMessageResult() {
        return isDisplayed(assertionSuccessfulRegistration);
    }
}
