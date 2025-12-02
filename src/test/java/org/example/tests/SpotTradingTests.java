package org.example.tests;

import org.example.enums.SpotTableHeaders;
import org.example.pages.DashboardPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.enums.AllCurrencies.*;

public class SpotTradingTests extends BaseTest {
    DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeMethod() {
        dashboardPage = new DashboardPage(driver);
    }

    @DataProvider
    public Object[] pairsDataProvider() {
        return new Object[]{
                BTC.toString(),
                USDT.toString()
        };
    }

    @DataProvider
    public Object[] tabsDataProvider() {
        return new Object[]{
                ALL.toString(),
                BTC.toString(),
                USDT.toString(),
                FIAT.toString()
        };
    }

    @Test(dataProvider = "pairsDataProvider")
    public void verifyCryptoTradingPairs(String coin) {
        Assert.assertTrue(dashboardPage
                .clickSpotTradingTab()
                .clickCurrencyTab(coin.toLowerCase())
                .clickShowMoreButton()
                .allPairsHaveCryptoSuffixes(coin));
    }

    @Test
    public void verifyFiatTradingPairs() {
        Assert.assertTrue(dashboardPage
                .clickSpotTradingTab()
                .clickCurrencyTab(FIAT.toString().toLowerCase())
                .clickShowMoreButton()
                .allPairsHaveFiatSuffixes());
    }

    @Test(dataProvider = "tabsDataProvider")
    public void allSpotTablesAreVisible(String tab) {
        Assert.assertTrue(dashboardPage
                .clickSpotTradingTab()
                .clickCurrencyTab(tab.toLowerCase())
                .clickShowMoreButton()
                .spotTablesAreVisible());
    }

    @Test(dataProvider = "tabsDataProvider")
    public void spotTableHeadersAreDisplayedInCorrectOrder(String tab) {
        String actualHeaders = dashboardPage
                .clickSpotTradingTab()
                .clickCurrencyTab(tab.toLowerCase())
                .extractSpotTableHeaders();
        Assert.assertEquals(actualHeaders, SpotTableHeaders.getAllValuesAsString());

    }


}
