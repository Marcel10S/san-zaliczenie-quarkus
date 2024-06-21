package org.zaliczenie.model;

public class Rate {
    private String currency;
    private String code;
    private double mid;

    public Rate(String currency, String code, double mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }
}
