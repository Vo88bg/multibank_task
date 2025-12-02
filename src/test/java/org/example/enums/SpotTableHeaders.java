package org.example.enums;

import java.util.Arrays;

public enum SpotTableHeaders {

    PAIR("Pair"),
    PRICE("Price"),
    CHANGE_24H("24h Change"),
    HIGH("High"),
    LOW("Low"),
    LAST_7_DAYS("Last 7 days");

    final String HEADER;

    SpotTableHeaders(String header) {
        this.HEADER = header;
    }

    public static String getAllValuesAsString() {
        return Arrays.stream(values())
                .toList()
                .stream()
                .map(SpotTableHeaders::getHeader)
                .toList()
                .toString();
    }

    public String getHeader() {
        return HEADER;
    }

}