package org.example.pages;

import org.example.enums.NavigationExpandableItems;
import org.example.enums.NavigationLinks;
import org.example.pages.aboutus.*;
import org.example.pages.features.*;
import org.example.pages.trading.*;
import org.example.pages.trading.TradingPage;
import org.example.utils.Page;
import org.example.utils.PageInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends Page {

    private final String NAVIGATION_LINK = "a[href='%s']";
    private final By EXPANDABLE_MENU = By.className("p-8");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickExpandableNavItem(NavigationLinks link){
        switch (link.PARENT){
            case "trade":
                waitForClickableElement(By.id(NavigationExpandableItems.TRADE.getID())).click();
                break;
            case "features":
                waitForClickableElement(By.id(NavigationExpandableItems.FEATURES.getID())).click();
                break;
            case "about us":
                waitForClickableElement(By.id(NavigationExpandableItems.ABOUT_US.getID())).click();
                break;
            case "support":
                waitForClickableElement(By.id(NavigationExpandableItems.SUPPORT.getID())).click();
                break;
        }
    }

    public PageInterface clickNavigationItem(NavigationLinks link){
        if (link.PARENT.equals("none")){
            waitForClickableElement(By.cssSelector(String.format(NAVIGATION_LINK, link))).click();
            return this;
        } else {
            clickExpandableNavItem(link);
            waitForVisibleElement(EXPANDABLE_MENU);
            waitForClickableElement(By.cssSelector(String.format(NAVIGATION_LINK, link))).click();
            return createPageBasedOnTheLink(link);
        }
    }




    public PageInterface createPageBasedOnTheLink(NavigationLinks link){
        switch (link.toString()){
            case "/trade/BTC_USD":
                return new TradingPage(this.driver);
            case "/markets":
                return new MarketsPage(this.driver);
            case "/":
                return this;
            case "/derivatives/BTCUST":
                return new DerivativesPage(this.driver);
            case "/trade/convert":
                return new ConvertPage(this.driver);
            case "https://multibank.io/features/spot-exchange":
                return new SpotExchangePage(this.driver);
            case "https://multibank.io/features/institutional":
                return new InstitutionalPage(this.driver);
            case "https://multibank.io/features/instant-buy":
                return new BuyAndSellPage(this.driver);
            case "https://multibank.io/about/why-multibank":
                return new WhyMultibankPage(this.driver);
            case "https://multibank.io/about/global-presence":
                return new GlobalPresencePage(this.driver);
            case "https://multibank.io/about/management":
                return new ManagementPage(this.driver);
            case "https://multibank.io/about/awards":
                return new AwardsPage(this.driver);
            case "https://multibank.io/about/sponsorship":
                return new SponsorshipPage(this.driver);
            case "https://multibank.io/about/blog":
                return new BlogPage(this.driver);
            case "https://multibank.io/about/milestones":
                return new MileStonesPage(this.driver);
            case "https://multibank.io/support/contact-us":
                return this;
            case "https://multibank.io/support/faq":
                return this;
                default: return this;
        }
    }

}
