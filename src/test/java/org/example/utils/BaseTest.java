package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void initializeDriver(@Optional("firefox") String browser) {

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
