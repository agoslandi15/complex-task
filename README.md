# Swag Labs Login Testing Project

## Description
This project tests the login functionality of the Swag Labs demo website using Selenium WebDriver. The tests verify different login scenarios as specified in the use cases below.

## Launch URL
https://www.saucedemo.com/

## Implementation

### Technologies Used
- **Test Automation Tool:** Selenium WebDriver
- **Project Builder:** Maven
- **Browsers Supported:**
    - Chrome
    - Edge
- **Locators:** XPath
- **Test Runner:** JUnit
- **Assertions:** Built-in assertions of JUnit
- **Running Environment:** Java 17

### Features
- **Design Patterns:**
    - Singleton: for the WebDriver instance
    - Facade: to perform the login in a single method (UC-3)
- **Logger:** SLF4J (info, debug, error)
- Data Provider for test parametrization


## Running the Tests
To execute all tests using the browser by default (chrome), run the following command:
```bash
mvn clean test
```

To execute all tests using a specific browser, you can use the following command:
```bash
mvn clean test -Dbrowser=chrome
```
or
```bash
mvn clean test -Dbrowser=edge
```