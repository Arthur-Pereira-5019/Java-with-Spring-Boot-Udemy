package com.arthur_pereira.First.REST.Endpoint.controllers;


import com.arthur_pereira.First.REST.Endpoint.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable("n1") String n1, @PathVariable("n2") String n2) {
        if (!isNumber(n1) || !isNumber(n2)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return convertToDouble(n1) + convertToDouble(n2);
    }

    @RequestMapping("/mul/{n1}/{n2}")
    public Double mul(@PathVariable("n1") String n1, @PathVariable("n2") String n2) {
        if (!isNumber(n1) || !isNumber(n2)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return convertToDouble(n1) * convertToDouble(n2);
    }

    @RequestMapping("/div/{n1}/{n2}")
    public Double div(@PathVariable("n1") String n1, @PathVariable("n2") String n2) {
        if (!isNumber(n1) || !isNumber(n2)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return convertToDouble(n1) / convertToDouble(n2);
    }

    @RequestMapping("/sub/{n1}/{n2}")
    public Double sub(@PathVariable("n1") String n1, @PathVariable("n2") String n2) {
        if (!isNumber(n1) || !isNumber(n2)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return convertToDouble(n1) - convertToDouble(n2);
    }

    @RequestMapping("/avg/{n1}/{n2}")
    public Double avg(@PathVariable("n1") String n1, @PathVariable("n2") String n2) {
        if (!isNumber(n1) || !isNumber(n2)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return (convertToDouble(n1) + convertToDouble(n2))/2;
    }

    @RequestMapping("/sqr/{n1}/{n2}")
    public Double sqr(@PathVariable("n1") String n1) {
        if (!isNumber(n1)) throw new UnsupportedMathOperationException("Please set a numeric value.");
        return Math.sqrt(convertToDouble(n1));
    }

    private Double convertToDouble(String n) {
        String number = n.replace(".", ",");
        return Double.valueOf(number);
    }

    private boolean isNumber(String n) {
        if (n == null || n.isEmpty()) {
            return false;
        }
        String number = n.replace(".", ",");
        return (n.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }
}
