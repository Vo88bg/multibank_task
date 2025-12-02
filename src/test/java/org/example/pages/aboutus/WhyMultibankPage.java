package org.example.pages.aboutus;

import org.example.utils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhyMultibankPage extends Page {

    private final By INITIAL_CARDS = By.cssSelector("div[class*=cardSection_card-div]");
    private final By ESTABLISHED_CARDS = By.cssSelector("div[class*=established_card-div]");
    private final By ADVANTAGES_CARDS = By.cssSelector("div[class*=advantages_card-div]");

    public WhyMultibankPage(WebDriver driver) {
        super(driver);
    }

    public boolean initialCardsAreDisplayed() {
        return getListOfElements(INITIAL_CARDS).size() == 4;
    }

    public boolean establishedCardsAreDisplayed() {
        return getListOfElements(ESTABLISHED_CARDS).size() == 6;
    }

    public boolean advantagesCardsAreDisplayed() {
        return getListOfElements(ADVANTAGES_CARDS).size() == 6;
    }

}