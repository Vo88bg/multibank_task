package org.example.enums;

public enum NavigationLinks {

    DASHBOARD("/", "none"),
    MARKET("/markets", "none"),
    TRADE_SPOT("/trade/BTC_USD", "trade"),
    TRADE_DERIVATIVES("/derivatives/BTCUST", "trade"),
    TRADE_CONVERT("/trade/convert", "trade"),
    FEATURES_SPOT_EXCHANGE("https://multibank.io/features/spot-exchange", "features"),
    FEATURES_INSTITUTIONAL("https://multibank.io/features/institutional", "features"),
    FEATURES_BUY_AND_SELL("https://multibank.io/features/instant-buy", "features"),
    ABOUT_US_WHY_MULTIBANK("https://multibank.io/about/why-multibank", "about us"),
    ABOUT_US_GLOBAL_PRESENCE("https://multibank.io/about/global-presence", "about us"),
    ABOUT_US_MANAGEMENT("https://multibank.io/about/management", "about us"),
    ABOUT_US_AWARDS("https://multibank.io/about/awards", "about us"),
    ABOUT_US_SPONSORSHIP("https://multibank.io/about/sponsorship", "about us"),
    ABOUT_US_BLOG("https://multibank.io/about/blog", "about us"),
    MILESTONES("https://multibank.io/about/milestones", "about us"),
    SUPPORT_CONTACT_US("https://multibank.io/support/contact-us", "support"),
    SUPPORT_FAQ("https://multibank.io/support/faq", "support");

    private final String LINK;
    public final String PARENT;

    NavigationLinks(String link, String parent) {
        this.LINK = link;
        this.PARENT = parent;
    }

    public String getFormattedURL() {

        if (this.LINK.startsWith("https://")) {
            return this.LINK;
        } else {
            return "https://trade.multibank.io" + this.LINK;
        }
    }

    @Override
    public String toString() {
        return this.LINK;
    }


}
