package com.sergiosanchez.katas.tdd;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String DEFAULT_SEPARATOR=",";
    
    public int add(String numbers) {
        String value = numbers;
        if (numbers.equals("")) {
            return 0;
        } else {
            String separator = DEFAULT_SEPARATOR;
            if (value.startsWith("//")) {
                separator=value.substring(2,3);
                value=value.substring(3);
            }
            value=value.replace("\n", separator);
            if (value.indexOf(separator)>-1) {
                List<Integer> numberList = Arrays.stream(value.split(separator))
                                                .filter(n -> !n.equals(""))
                                                .map(n -> Integer.valueOf(n))
                                                .collect(Collectors.toList());
                int sum = 0;
                for (Integer n : numberList) {
                    if (n<0) {
                        throw new InvalidParameterException( "negatives not allowed " + n);
                    };
                    sum+=n;
                }
                return sum;

            } else {
                return Integer.parseInt(value);
            }
        }
    }
}