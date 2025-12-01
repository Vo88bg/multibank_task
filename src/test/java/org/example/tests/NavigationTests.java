package org.example.tests;

import org.example.enums.NavigationLinks;
import org.example.pages.DashboardPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        dashboardPage = new DashboardPage(driver);
    }

    @DataProvider
    public Object[] linksDataProvider() {
        return NavigationLinks.values();
    }

    @Test(dataProvider = "linksDataProvider")
    public void verifyCorrectRedirections(NavigationLinks link) {
        String expectedURL = link.getFormattedURL();
        String actualURL = dashboardPage.clickNavigationItem(link).getUrl(expectedURL);
        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void verifyInstantBuyPopUp() {
        Assert.assertTrue(dashboardPage
                .clickInstantBuyNavItem()
                .instantBuyPopUpIsDisplayed());
    }


}
