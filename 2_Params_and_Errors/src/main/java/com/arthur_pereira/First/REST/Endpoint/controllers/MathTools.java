package com.arthur_pereira.First.REST.Endpoint.controllers;

public class MathTools {


    public static Double convertToDouble(String n) {
        String number = n.replace(".", ",");
        return Double.valueOf(number);
    }

    public static boolean isNumber(String n) {
        if (n == null || n.isEmpty()) {
            return false;
        }
        String number = n.replace(".", ",");
        return (n.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }


}
