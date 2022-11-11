package com.project.www.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    public static final String PAGE_URL = "www.login.com";
    // WEB ELEMENTS
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "_submit")
    private WebElement loginButton;

    @FindBy(className = "msg-error")
    private WebElement errorMessageLabel;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // GETTETS & SETTERS
    @Override
    public WebElement getPageLoadedTestElement() {
        return usernameInput;
    }


    // METHODS
    public LoginPage enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }
}
