package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class WebDriverSingleton {
    private static WebDriver driver;
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";

    //Parametrized and private constructor
    private WebDriverSingleton(String browser) {
        LoggerUtil.info("Starting driver: " + browser);

        try {
            switch (browser.toLowerCase()) {
                case EDGE: {
                    LoggerUtil.debug("Setting up Edge WebDriver");
                    System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                    driver = new EdgeDriver();
                    LoggerUtil.info("Edge Driver successfully configured");
                    break;
                }
                default: {
                    LoggerUtil.debug("Setting up Chrome WebDriver");
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    driver = new ChromeDriver();
                    LoggerUtil.info("Chrome Driver successfully configured");
                    break;
                }
            }
            driver.manage().window().maximize();

        } catch (Exception exc) {
            LoggerUtil.error("Error with the configuration of the " + browser + " driver: " + exc.getLocalizedMessage());
            throw new RuntimeException("The WebDriver could not be initialized" + exc.getMessage());
        }
    }

    //Parametrized method (only returns the instance)
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", CHROME);
            new WebDriverSingleton(browser);
        } else {
            LoggerUtil.debug("Using an already existing driver");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            LoggerUtil.debug("Closing WebDriver");
            try {
                driver.quit();
                LoggerUtil.info("WebDriver successfully closed");
                LoggerUtil.info("-------------------------------------------------------------------------------------");
            } catch (Exception exc) {
                LoggerUtil.error("Error closing WebDriver: " + exc.getMessage());
            } finally {
                driver = null;
            }
        } else {
            LoggerUtil.debug("There's no WebDriver to close");
        }
    }
}