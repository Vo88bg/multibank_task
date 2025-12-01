package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Page implements PageInterface {

    private final By LOADING_ELEMENT = By.cssSelector("*[class*=_skeleton_]");
    protected WebDriver driver;
    WebDriverWait explicitWait;
    Wait<WebDriver> fluentWait;
    protected JavascriptExecutor js;
    Actions actions;
    protected Logger log;

    public Page(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofMillis(10000));
        actions = new Actions(driver);
        log = LoggerFactory.getLogger(Page.class);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(TimeoutException.class);
    }

    protected void populateInputField(By by, String text) {// it is not used, but I will leave it as a method that might be useful for future tests
        log.info("Populating input fields with text: {}", text);
        WebElement webElement = driver.findElement(by);
        webElement.sendKeys("");// in case that the input field is pre-populated with unwanted information
        webElement.sendKeys(text);
    }

    protected void populateInputField(By by, int num) {// it is not used, but I will leave it as a method that might be useful for future tests
        log.info("Populating numeric input field with: {}", num);
        WebElement webElement = driver.findElement(by);
        webElement.sendKeys("");// in case that the input field is pre-populated with unwanted information
        webElement.sendKeys(String.valueOf(num));
    }

    protected WebElement waitForClickableElement(By element) {
        log.info("Waiting for clickable element ".concat(element.toString()));
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);
    }

    protected WebElement waitForClickableElement(By element, Duration duration) {// it is not used, but I will leave it as a method that might be useful for future tests
        log.info("Waiting for clickable element {} with duration of {}", element.toString(), duration.toString());
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);
    }

    protected WebElement waitForElement(By element) {
        log.info("Waiting for element {}", element.toString());
        explicitWait.until(d -> (driver.findElement(element)));
        return driver.findElement(element);
    }

    protected void waitForLoadingToFinish() {// it is not used, but I will leave it as a method that might be useful for future tests
        log.info("Waiting for the loading element to disappear");
        List<WebElement> loadingElement = driver.findElements(LOADING_ELEMENT);
        while (!loadingElement.isEmpty()) {
            explicitWait.until(ExpectedConditions.invisibilityOf(loadingElement.get(0)));
        }
    }

    protected void hoverOverElement(By element) {
        log.info("Hovering over element {}", element.toString());
        WebElement webElement = driver.findElement(element);
        actions.moveToElement(webElement).perform();
    }

    protected void waitForVisibleElement(By element) {
        log.info("Waiting for visible element {}", element.toString());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected String waitForTextToChange(WebElement element, String text, Duration duration) {
        log.info("Waiting for text ".concat(String.format("\"%s\"", text)).concat(" to be changed"));
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(d -> !element.getText().equals(text));
        return element.getText();
    }

    protected List<WebElement> getListOfElements(By elements) {
        log.info("Getting list of elements {}", elements.toString());
        waitForElement(elements);//wait for the first element of the list to appear
        return driver.findElements(elements);
    }

    protected void scrollIntoView(WebElement element) {
        log.info("Scrolling to ".concat(element.getTagName()));
        js.executeScript("arguments[0].scrollIntoView({block: 'start'});", element);
    }

    @Override
    public String getUrl(String expectedURL) {
        if(!expectedURL.equals("https://trade.multibank.io/")) {//we want to be sure that the page loaded and the url changed before getting it
            explicitWait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://trade.multibank.io/")));
        } else {//we also want to make sure that we will get the base url, when redirecting to such page
            explicitWait.until(ExpectedConditions.urlToBe("https://trade.multibank.io/"));
        }
        String url = driver.getCurrentUrl();

        assert url != null;
        return url.split("\\?")[0];
    }
}
