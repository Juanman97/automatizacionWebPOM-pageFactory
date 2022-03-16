package com.parasoft.parabank.page.login;


import com.parasoft.parabank.model.login.LoginModel;
import com.parasoft.parabank.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends CommonActionOnPages {

    private static Logger LOGGER = Logger.getLogger(LoginPage.class);
    private LoginModel loginModel;

    //Localizadores para input de datos
    @CacheLookup
    @FindBy(name = "username")
    private WebElement username;

    @CacheLookup
    @FindBy(name = "password")
    private WebElement password;

    @CacheLookup
    @FindBy(xpath = "//*[@class= 'button' and @value = 'Log In']")
    private WebElement loginButton;

    //Localizadores para assertions
    @CacheLookup
    @FindBy(xpath = "//th[text() = 'Account']")
    private WebElement assertionAcountHeader;

    @CacheLookup
    @FindBy(xpath = "//th[text() = 'Balance*']")
    private WebElement assertionBalanceHeader;

    @CacheLookup
    @FindBy(xpath = "//th[text() = 'Available Amount']")
    private WebElement assertionAvailableAmountHeader;

    @CacheLookup
    @FindBy(xpath = "//p[@class = 'error' and text() = 'Please enter a username and password.']")
    private WebElement assertionErrorMessage;

    //constructor
    public LoginPage(WebDriver driver, int seconds, LoginModel loginModel) {
        super(driver, seconds);
        pageFactoryInitElement(driver, this);
        this.loginModel = loginModel;
    }

    public LoginPage(WebDriver driver, int seconds) {
        super(driver, seconds);
        pageFactoryInitElement(driver, this);
    }

    //funcionalidades
    public void failedLogin() {
        scroll(loginButton);
        submit(loginButton);
    }
    public void successfulLogin() {
        scroll(username);
        clear(username);
        typeOn(username, loginModel.getUsername());

        scroll(password);
        clear(password);
        typeOn(password, loginModel.getPassword());

        failedLogin();
    }

    public List<Boolean> successfulLoginResult() {
        List<Boolean> headersDisplayed = new ArrayList<>();
        headersDisplayed.add(isDisplayed(assertionAcountHeader));
        headersDisplayed.add(isDisplayed(assertionBalanceHeader));
        headersDisplayed.add(isDisplayed(assertionAvailableAmountHeader));

        return headersDisplayed;
    }

    public boolean failedLoginResult() {
        return isDisplayed(assertionErrorMessage);
    }

}
