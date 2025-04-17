package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverSingleton.class);
    private static WebDriver driver;

    private WebDriverSingleton() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            logger.info("Initializing WebDriver for browser: " + browserType);
            initializeDriver(browserType);
        }
        return driver;
    }

    private static void initializeDriver(String browserType) {
        try {
            if (browserType.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browserType.equalsIgnoreCase("edge")) {
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            logger.info("Quitting WebDriver");
            driver.quit();
            driver = null;
        }
    }
}