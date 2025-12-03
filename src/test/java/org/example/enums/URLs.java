package org.example.enums;

public enum URLs {

    GOOGLE_PLAY("https://play.google.com/store/apps/details?id=com.multibank.app&pli=1"),
    APPLE_APP_STORE("https://apps.apple.com/ae/app/multibank-io-buy-btc-crypto/id1592119946");

    private final String url;

    URLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
