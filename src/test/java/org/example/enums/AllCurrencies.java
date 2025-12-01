package org.example.enums;

public enum AllCurrencies {
    BTC("BTC"),
    USDT("USDT"),
    USD("USD"),
    AED("AED"),
    EUR("EUR"),
    ALL("ALL"),
    FIAT("FIAT");

    final String CURRENCY;

    AllCurrencies(String currency) {
        this.CURRENCY = currency;
    }

    public String getCURRENCY(){
        return this.CURRENCY;
    }
}
