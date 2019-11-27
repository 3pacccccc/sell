package com.immoc.sell.utils;

public class MathUtil {

    private static final double MONEY_RANGE = 0.01;

    public static Boolean equels(double d1, double d2) {
        double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        }
        return false;
    }
}
