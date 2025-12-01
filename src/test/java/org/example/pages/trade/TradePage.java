package org.example.pages.trade;

import org.example.utils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TradePage extends Page {

    private final By INSTANT_BUY_POP_UP = By.cssSelector("div[id*=headlessui-dialog-panel]");

    public TradePage(WebDriver driver) {
        super(driver);
    }


    public boolean instantBuyPopUpIsDisplayed() {
        return waitForElement(INSTANT_BUY_POP_UP).isDisplayed();
    }

}
