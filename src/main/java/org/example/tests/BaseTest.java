package org.example.tests;

import org.example.models.BasePage;
import org.example.models.LoginPage;
import org.example.utils.LoggerUtil;
import org.example.utils.WebDriverSingleton;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

public class BaseTest {
    protected WebDriver driver;
    protected String browser;
    protected BasePage basePage;
    protected LoginPage loginPage;

    private String URL = "https://www.saucedemo.com/";

    public BaseTest(String browser) {
        this.browser = browser;
    }

    @BeforeEach
    public void setUp() {
        LoggerUtil.info("Setting up WebDriver: " + browser);
        driver = WebDriverSingleton.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(URL);
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        LoggerUtil.info("Tearing down WebDriver");
        WebDriverSingleton.quitDriver();
    }

    // Data provider for browsers (used in test UC-1 and UC-2)
    static Stream<Arguments> provideBrowsers() {
        return Stream.of(
                Arguments.of("chrome"),
                Arguments.of("edge")
        );
    }

    // Data provider for users (used in test UC-2)
    static Stream<Arguments> provideValidUsers() {
        return Stream.of(
                Arguments.of("standard_user", "secret_sauce"),
                Arguments.of("problem_user", "secret_sauce"),
                Arguments.of("performance_glitch_user", "secret_sauce")
        );
    }

    //Data provider for valid users and passwords (used in test UC-3)
    static Stream<Arguments> provideValidCredentials(){
        return Stream.of(
                Arguments.of("chrome", "standard_user", "secret_sauce"),
                Arguments.of("chrome", "problem_user", "secret_sauce"),
                Arguments.of("chrome", "performance_glitch_user", "secret_sauce"),
                Arguments.of("edge", "standard_user", "secret_sauce"),
                Arguments.of("edge", "problem_user", "secret_sauce"),
                Arguments.of("edge", "performance_glitch_user", "secret_sauce")
        );
    }

}

