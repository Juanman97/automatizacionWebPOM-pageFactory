package com.parasoft.parabank.page.customcare;

import com.parasoft.parabank.model.customcare.CustomCareModel;
import com.parasoft.parabank.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CustomCarePage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(CustomCarePage.class);
    private CustomCareModel customCareModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo";

    //Localizadores para navegación
    @CacheLookup
    @FindBy(linkText = "Contact Us")
    private WebElement contactUs;

    //Localizadores para input de datos
    @CacheLookup
    @FindBy(id = "name")
    private WebElement name;

    @CacheLookup
    @FindBy(id = "email")
    private WebElement email;

    @CacheLookup
    @FindBy(id = "phone")
    private WebElement phone;

    @CacheLookup
    @FindBy(id = "message")
    private WebElement message;

    @CacheLookup
    @FindBy(xpath = "//input[@type = 'submit' and @value = 'Send to Customer Care']")
    private WebElement submit;

    //Localizadores para assertions
    @CacheLookup
    @FindBy(id = "name.errors")
    private WebElement assertionNameRequired;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"rightPanel\"]/p[1]")
    private WebElement assertionName;

    //constructor
    public CustomCarePage(WebDriver driver, int seconds, CustomCareModel customCareModel) {
        super(driver, seconds);
        pageFactoryInitElement(driver, this);
        this.customCareModel = customCareModel;
    }

    //funcionalidades
    public void goToCustomerCare() {
        scroll(contactUs);
        click(contactUs);
    }

    public void sendFilledForm() {
        scroll(name);
        clear(name);
        typeOn(name, customCareModel.getName());

        scroll(email);
        clear(email);
        typeOn(email, customCareModel.getEmail());

        scroll(phone);
        clear(phone);
        typeOn(phone, customCareModel.getPhone());

        scroll(message);
        clear(message);
        typeOn(message, customCareModel.getMessage());

        scroll(submit);
        submit(submit);
    }

    public void sendEmptyForm() {
        scroll(submit);
        submit(submit);
    }

    public String filledFormSubmitResult() {
        String result;
        result = getText(assertionName).trim();
        result = result.replace("Thank you ", "");
        return result;
    }

    public boolean emptyFormSubmitResult() {
        return isDisplayed(assertionNameRequired);
    }

}
