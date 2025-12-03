package org.example.enums;

public enum CryptoCurrencies {
    BTC("BTC"),
    USDT("USDT");

    final String CURRENCY;

    CryptoCurrencies(String currency) {
        this.CURRENCY = currency;
    }

    public String getCURRENCY() {
        return this.CURRENCY;
    }
}
