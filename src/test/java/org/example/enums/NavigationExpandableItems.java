package org.example.enums;

public enum NavigationExpandableItems {

    TRADE("trade-header-option-open-button"),
    FEATURES("features-header-option-open-button"),
    ABOUT_US("about-header-option-open-button"),
    SUPPORT("support-header-option-open-button");

    final String ID;

    NavigationExpandableItems(String id) {
        ID = id;
    }

    public String getID() {
        return this.ID;

    }


}
