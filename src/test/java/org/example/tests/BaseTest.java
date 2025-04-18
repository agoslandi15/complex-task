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
    protected BasePage basePage;
    protected LoginPage loginPage;

    private String URL = "https://www.saucedemo.com/";


    @BeforeEach
    public void setUp() {
        LoggerUtil.info("Setting up WebDriver");
        driver = WebDriverSingleton.getDriver();
        driver.get(URL);
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        LoggerUtil.info("Tearing down WebDriver");
        WebDriverSingleton.closeDriver();
    }

    // Data provider for users (used in test UC-2)
    static Stream<Arguments> provideValidUsers() {
        return Stream.of(
                Arguments.of("standard_user"),
                Arguments.of("locked_out_user"),
                Arguments.of("problem_user"),
                Arguments.of("performance_glitch_user"),
                Arguments.of("error_user"),
                Arguments.of("visual_user")
        );
    }

    //Data provider for valid users and passwords (used in test UC-3)
    static Stream<Arguments> provideValidCredentials(){
        return Stream.of(
                Arguments.of("standard_user", "secret_sauce"),
                Arguments.of("problem_user", "secret_sauce"),
                Arguments.of("performance_glitch_user", "secret_sauce"),
                Arguments.of("error_user", "secret_sauce"),
                Arguments.of("visual_user", "secret_sauce")
        );
    }

}

