package org.example.models;

import org.example.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception exc) {
            LoggerUtil.error("Error with the " + locator + " locator: " + exc.getLocalizedMessage());
            return null;
        }
    };

    protected void click(By locator) {
        findElement(locator).click();
    }

    protected void sendText(By locator, String text){
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clear(By locator){
        findElement(locator).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception exc) {
            LoggerUtil.error("Error with the " + locator + " locator: " + exc.getLocalizedMessage());
            return false;
        }
    }

    protected String getText(By locator) {
        try {
            return findElement(locator).getText();
        } catch (Exception exc) {
            LoggerUtil.error("Error with the " + locator + " locator: " + exc.getLocalizedMessage());
            throw exc;
        }
    }

    protected String getTitle() {
        return driver.getTitle();
        }
}
