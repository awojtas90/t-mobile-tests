package models;


public record Rate(
        String currency,
        String code,
        double mid
) {}
