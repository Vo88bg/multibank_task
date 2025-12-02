package org.example.tests;

import org.example.pages.DashboardPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MarketingBannerTests extends BaseTest {

    DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void verifyThatMarketingBannersAreVisible(){
       Assert.assertTrue(dashboardPage.marketingBannersAreVisible());
    }

}
