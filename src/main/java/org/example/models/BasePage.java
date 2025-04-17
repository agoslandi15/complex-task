package org.example.models;

import org.example.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception exc) {
            LoggerUtil.error("Element with XPath: '" + locator.toString() + "' not found");
            throw exc;
        }
    };

    protected void click(By locator) {
        LoggerUtil.info("Clicking on element with XPath: " + locator.toString());
        findElement(locator).click();
    }

    protected void setField(By locator, String text){
        LoggerUtil.info("Typing '" + text + "' in element with XPath: " + locator);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clear(By locator){
        LoggerUtil.info("Clearing field with XPath: " + locator.toString());
        findElement(locator).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception exc) {
            LoggerUtil.warn("Element with XPath: '" + locator.toString() + "' is not displayed");
            return false;
        }
    }

    protected String getText(By locator) {
        try {
            LoggerUtil.info("Getting text from element with XPath: " + locator.toString());
            return findElement(locator).getText();
        } catch (Exception exc) {
            LoggerUtil.error("Failed to get text from element with XPath: '" + locator.toString() + "'");
            throw exc;
        }
    }

    protected String getTitle() {
        String title = driver.getTitle();
        LoggerUtil.info("Getting page title: " + title);
        return title;
        }
}
