package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * The BaseTest class controls the lifecycle of all tests in terms of launching and closing a test.
 * It allows the choice of a browser, as well as closing the browser's instance.
 * <p>
 * Running the tests:
 * - run all tests in parallel using the following command: mvn test -DsuiteXmlFile=testng.xml
 * By default the test are executed in Chrome. In order to run the tests on another browser execute the command as follows: mvn test -DsuiteXmlFile=testng.xml -Dbrowser=firefox
 * - run a single test via IDE configuration and make sure to pass -Dbrowser as parameter.
 * <p>
 * The available options for the -Dbrowser parameters are chrome, firefox and safari.
 * If the desired browser is Google Chrome, then the parameter should be passed as follows: -Dbrowser=chrome
 */

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void initializeDriver(@Optional("chrome") String browser) {

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("No such browser " + browser);
        }
        openURL();
    }


    private void openURL() {
        driver.manage().window().maximize();
        driver.get("https://trade.multibank.io/");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

}
