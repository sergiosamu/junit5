package com.sergiosanchez.katas.tdd;

import java.util.Arrays;

public class StringCalculator {

    
    public int add(String numbers) {

        if (numbers.trim().equals("")) {
            return 0;
        } else {
            numbers=numbers.replace("\n", ",");
            if (numbers.indexOf(",")>-1) {
                return Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
            } else {
                return Integer.parseInt(numbers);
            }
        }
    }
}