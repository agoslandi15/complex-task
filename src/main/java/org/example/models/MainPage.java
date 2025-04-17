package org.example.models;

import org.example.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private final By mainPageLogo = By.xpath("//div[@class='app_logo']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMainPageDisplayed() {
        LoggerUtil.info("Checking if main page logo is displayed");
        return isElementDisplayed(mainPageLogo);
    }

    public String getMainPageTitle() {
        LoggerUtil.info("Getting main page logo text");
        return getTitle();
    }
}
