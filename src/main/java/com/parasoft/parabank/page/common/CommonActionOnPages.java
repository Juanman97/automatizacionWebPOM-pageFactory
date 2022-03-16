package com.parasoft.parabank.page.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CommonActionOnPages extends BaseSikuli{
    private static final Logger LOGGER = Logger.getLogger(CommonActionOnPages.class);
    private WebDriver driver;
    private WebDriverWait webDriverExplicitWait; //para tiempos explícitos
    private final String WEBDRIVER_NULL_MESSAGE = "\nWARNING!\n\rThe Webdriver is null, please check it.\n";

    //constructor con explicit wait
    public CommonActionOnPages(WebDriver driver, int seconds) {
        try {
            if (driver == null) {
                LOGGER.warn(WEBDRIVER_NULL_MESSAGE);
            } else {
                this.driver = driver;
                webDriverExplicitWait = new WebDriverWait(driver, seconds);
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    //Inicializar page factory
    protected void pageFactoryInitElement(WebDriver driver, Object page) {
        PageFactory.initElements(driver, page);
    }

    //funcionalidades con page factory
    protected void clear(WebElement webElement) {
        webDriverExplicitWait.until(elementToBeClickable(webElement)).clear();
    }

    protected void click(WebElement webElement) {
        webDriverExplicitWait.until(elementToBeClickable(webElement)).click();
    }

    protected void typeOn(WebElement webElement, CharSequence... text) {
        webDriverExplicitWait.until(elementToBeClickable(webElement)).sendKeys(text);
    }

    protected void submit(WebElement webElement) {
        webDriverExplicitWait.until(elementToBeClickable(webElement)).submit();
    }

    protected void scroll(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    protected String getText(WebElement webElement) {
        return webDriverExplicitWait.until(elementToBeClickable(webElement)).getText();
    }

    protected void selectDropdownList(WebElement webElement, String text) {
        Select selectList = new Select(webElement);
        selectList.selectByVisibleText(text);
    }

    protected boolean isDisplayed(WebElement webElement) {
        return webDriverExplicitWait.until(elementToBeClickable(webElement)).isDisplayed();
    }


}
