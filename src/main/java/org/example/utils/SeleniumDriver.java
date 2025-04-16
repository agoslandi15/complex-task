package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumDriver {
    private static WebDriver driver;

    private SeleniumDriver() {
        String browserType = "chrome";
        if (browserType.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            new SeleniumDriver();
        }
        return driver;
    }

}
