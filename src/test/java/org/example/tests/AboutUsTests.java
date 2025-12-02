package org.example.tests;

import org.example.pages.DashboardPage;
import org.example.pages.aboutus.WhyMultibankPage;
import org.example.utils.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutUsTests extends BaseTest {

    DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void whyMultibankPageLoadsAllInformationTest() {
        WhyMultibankPage whyMultibankPage = dashboardPage
                .clickWhyMultiBankNavItem();

        whyMultibankPage.softAssert.assertTrue(whyMultibankPage.initialCardsAreDisplayed());
        whyMultibankPage.softAssert.assertTrue(whyMultibankPage.establishedCardsAreDisplayed());
        whyMultibankPage.softAssert.assertTrue(whyMultibankPage.advantagesCardsAreDisplayed());
    }

}
