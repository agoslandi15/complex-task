package org.example.tests;

import org.example.models.LoginPage;
import org.example.models.MainPage;
import org.example.utils.LoggerUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest extends BaseTest{

    @DisplayName("UC-1: Login with empty credentials")
    @Test
    @Tag("UC-1")
    public void testWithEmptyCredentials() {
        LoggerUtil.info("Starting running test UC-1");

        loginPage.enterUsername("any_username");
        loginPage.enterPassword("any_password");
        loginPage.clearUsernameField();
        loginPage.clearPasswordField();
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorText(),
                "Error message should be 'Epic sadface: Username is required'");

        LoggerUtil.info("Finished running test UC-1 successfully");
    }

    @DisplayName("UC-2: Login with empty password field")
    @ParameterizedTest(name = "User: {0}")
    @MethodSource("provideValidUsers")
    @Tag("UC-2")
    public void testWithEmptyPassword(String username) {
        LoggerUtil.info("Starting running test UC-2 with username: " + username);

        loginPage.enterUsername(username);
        loginPage.enterPassword("any_password");
        loginPage.clearPasswordField();
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorText(),
                "Error message should be 'Epic sadface: Password is required'");

        LoggerUtil.info("Finished running test UC-2 successfully");
    }

    @DisplayName("UC-3: Login with valid credentials in both fields")
    @ParameterizedTest(name = "User: {0}, Password: {1}")
    @MethodSource("provideValidCredentials")
    @Tag("UC-3")
    public void testWithValidCredentials(String username, String password) {
        LoggerUtil.info("Starting running test UC-3 with username: " + username + "| password: " + password);

        MainPage mainPage = loginPage.performLogin(username, password);

        assertTrue(mainPage.isMainPageDisplayed(), "Main page should be loaded");
        assertEquals("Swag Labs", mainPage.getMainPageTitle(), "Title should be 'Swag Labs'");

        LoggerUtil.info("Finished running test UC-3 successfully");
    }

}
