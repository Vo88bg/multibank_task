package org.example.pages;

import org.example.utils.Page;
import org.openqa.selenium.WebDriver;

/**
 * This class will be used only for the methods related to tab/window management.
 * It will be used only, when opening external pages, like the ones of Google Play Store and Apple's app store
 **/
public class ExternalPage extends Page {
    public ExternalPage(WebDriver driver) {
        super(driver);
    }
}
