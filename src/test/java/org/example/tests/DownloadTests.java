package org.example.tests;

import org.example.enums.URLs;
import org.example.pages.DashboardPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DownloadTests extends BaseTest {

    DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void androidAppRedirectionTest() {
        String androidStoreUrl = URLs.GOOGLE_PLAY.getUrl();
        String actualUrl = dashboardPage
                .clickGooglePlayButton()
                .getUrl(androidStoreUrl);
        Assert.assertEquals(actualUrl, androidStoreUrl);
    }

    @Test
    public void iosAppRedirectionTest() {
        String appleStoreUrl = URLs.APPLE_APP_STORE.getUrl();
        String actualUrl = dashboardPage
                .clickAppleAppStoreButton()
                .getUrl(appleStoreUrl);
        Assert.assertEquals(actualUrl, appleStoreUrl);
    }

}
