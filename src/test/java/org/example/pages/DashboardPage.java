package org.example.pages;

import org.example.enums.NavigationExpandableItems;
import org.example.enums.NavigationLinks;
import org.example.pages.aboutus.*;
import org.example.pages.features.BuyAndSellPage;
import org.example.pages.features.InstitutionalPage;
import org.example.pages.features.SpotExchangePage;
import org.example.pages.support.ContactUsPage;
import org.example.pages.support.FAQPage;
import org.example.pages.trade.ConvertPage;
import org.example.pages.trade.DerivativesPage;
import org.example.pages.trade.TradePage;
import org.example.utils.Page;
import org.example.utils.PageInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.enums.CryptoCurrencies.values;

public class DashboardPage extends Page {

    private final String NAVIGATION_LINK = "a[href='%s']";
    private final By EXPANDABLE_MENU = By.className("p-8");
    private final By INSTANT_BUY_LINK = By.xpath("//div[text()='Instant Buy']/ancestor::div[1]");
    private final By WHY_MULTIBANK_LINK = By.xpath("//div[text()='Why Multibank?']/ancestor::div[1]");
    private final By SPOT_TRADING_TAB = By.xpath("//span[text()='Spot']");
    private final By SHOW_MORE_BUTTON = By.cssSelector("div[class*=style_show-more-container] > button");
    private final By TABLE_ROWS = By.cssSelector("tr[id*=-td]");
    private final By TABLE_HEADERS = By.cssSelector("div[class*=style_table] th");
    private final By SYMBOLS_CELL = By.cssSelector("td[id*=_base-td]");
    private final By MARKETING_BANNERS = By.cssSelector(".slick-slide img");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private void clickNavItemFromExpandableElement(NavigationLinks link) {
        switch (link.PARENT) {
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

    public DashboardPage clickSpotTradingTab() {
        waitForClickableElement(SPOT_TRADING_TAB).click();
        return this;
    }

    public DashboardPage clickCurrencyTab(String id) {
        waitForElement(By.id(id)).click();
        return this;
    }

    public DashboardPage expandNavItem(NavigationExpandableItems item) {
        waitForClickableElement(By.id(item.getID())).click();
        return this;
    }

    public DashboardPage clickShowMoreButton() {
        waitForClickableElement(SHOW_MORE_BUTTON).click();
        return this;
    }

    public boolean allPairsHaveCryptoSuffixes(String coin) {
        List<String> symbols = extractTextFromListOfElements(SYMBOLS_CELL);
        for (String symbol : symbols) {
            if (!symbol.endsWith(coin)) {
                log.error("Symbol " + symbol + " does not end with " + coin);
                return false;
            }
        }

        return true;
    }

    public boolean allPairsHaveFiatSuffixes() {
        List<String> symbols = extractTextFromListOfElements(SYMBOLS_CELL);

        for (String symbol : symbols) {
            for (int i = 0; i < values().length; i++) {
                System.out.println(symbol);
                System.out.println(values()[i]);
                if (symbol.endsWith(values()[i].getCURRENCY())) {
                    return false;
                }
            }
        }
        return true;
    }

    public TradePage clickInstantBuyNavItem() {
        expandNavItem(NavigationExpandableItems.TRADE);
        waitForVisibleElement(EXPANDABLE_MENU);
        waitForClickableElement(INSTANT_BUY_LINK).click();
        return new TradePage(driver);
    }

    public WhyMultibankPage clickWhyMultiBankNavItem() {
        expandNavItem(NavigationExpandableItems.ABOUT_US);
        waitForVisibleElement(EXPANDABLE_MENU);
        waitForClickableElement(WHY_MULTIBANK_LINK).click();
        return new WhyMultibankPage(driver);
    }

    public PageInterface clickNavigationItem(NavigationLinks link) {
        if (link.PARENT.equals("none")) {
            waitForClickableElement(By.cssSelector(String.format(NAVIGATION_LINK, link))).click();
            return this;
        } else {
            clickNavItemFromExpandableElement(link);
            waitForVisibleElement(EXPANDABLE_MENU);
            waitForClickableElement(By.cssSelector(String.format(NAVIGATION_LINK, link))).click();
            return createPageBasedOnTheLink(link);
        }
    }


    public PageInterface createPageBasedOnTheLink(NavigationLinks link) {
        return switch (link.toString()) {
            case "/trade/BTC_USD" -> new TradePage(this.driver);
            case "/markets" -> new MarketsPage(this.driver);
            case "/derivatives/BTCUST" -> new DerivativesPage(this.driver);
            case "/trade/convert" -> new ConvertPage(this.driver);
            case "https://multibank.io/features/spot-exchange" -> new SpotExchangePage(this.driver);
            case "https://multibank.io/features/institutional" -> new InstitutionalPage(this.driver);
            case "https://multibank.io/features/instant-buy" -> new BuyAndSellPage(this.driver);
            case "https://multibank.io/about/why-multibank" -> new WhyMultibankPage(this.driver);
            case "https://multibank.io/about/global-presence" -> new GlobalPresencePage(this.driver);
            case "https://multibank.io/about/management" -> new ManagementPage(this.driver);
            case "https://multibank.io/about/awards" -> new AwardsPage(this.driver);
            case "https://multibank.io/about/sponsorship" -> new SponsorshipPage(this.driver);
            case "https://multibank.io/about/blog" -> new BlogPage(this.driver);
            case "https://multibank.io/about/milestones" -> new MileStonesPage(this.driver);
            case "https://multibank.io/support/contact-us" -> new ContactUsPage(this.driver);
            case "https://multibank.io/support/faq" -> new FAQPage(this.driver);
            default -> this;
        };
    }


    public boolean spotTablesAreVisible() {
        List<WebElement> rows = getListOfElements(TABLE_ROWS);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            softAssert.assertEquals(cells.size(), 7);
            for (WebElement cell : cells)
                if (!cell.isDisplayed()) return false;

        }
        return true;
    }

    public String extractSpotTableHeaders() {
        return getListOfElements(TABLE_HEADERS)
                .stream()
                .map(WebElement::getText)
                .filter(e -> !e.trim().isEmpty())//we want to remove the first header, as it is empty
                .toList()
                .toString();
    }

    public boolean marketingBannersAreVisible() {
        return !getListOfElements(MARKETING_BANNERS).isEmpty();
    }

}
