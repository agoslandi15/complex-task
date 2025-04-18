package org.example.models;

import org.example.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By usernameField = By.xpath("//input[@id='user-name']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username){
        LoggerUtil.info("Entering username: " + username);
        setField(usernameField, username);
    }

    public void enterPassword(String password){
        LoggerUtil.info("Entering password: " + password);
        setField(passwordField, password);
    }

    public MainPage performLogin(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new MainPage(driver);
    }

    public void clickLoginButton(){
        LoggerUtil.info("Clicking login button");
        click(loginButton);
    }

    public void clearUsernameField(){
        LoggerUtil.info("Clearing username field");
        clear(usernameField);
    }

    public void clearPasswordField(){
        LoggerUtil.info("Clearing password field");
        clear(passwordField);
    }

    public String getErrorText(){
        LoggerUtil.info("Getting error message text");
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed(){
        LoggerUtil.info("Checking if error message is displayed");
        return isElementDisplayed(errorMessage);
    }

}
