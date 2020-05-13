package com.sergiosanchez.katas.tdd;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final int MAX_SUM_NUMBER= 1000;
    private static final String DEFAULT_SEPARATOR=",";
    
    public int add(String numbers) {
        String value = numbers;
        if (numbers.equals("")) {
            return 0;
        } else {
            String[] separatorList= new String[] {DEFAULT_SEPARATOR};
            if (value.startsWith("//[")) {
                int separatorEndIndex = value.lastIndexOf("]");
                separatorList = value.substring(3,separatorEndIndex).split(Pattern.quote("]["));
                value=value.trim().substring(separatorEndIndex + 1);
            } else if (value.startsWith("//")) {
                separatorList[0] = value.substring(2,3);
                value=value.substring(3);
            } 

            value=value.replace("\n", separatorList[0]);

            String separatorPattern = Arrays.stream(separatorList)
                                                    .map(s -> Pattern.quote(s))
                                                    .collect(Collectors.joining("|"));
            
            List<Integer> numberList = Arrays.stream(value.split(separatorPattern))
                                            .filter(n -> !n.equals(""))
                                            .map(n -> Integer.valueOf(n))
                                            .collect(Collectors.toList());
            int sum = 0;
            List<Integer> negativesList = new ArrayList<Integer>();
            for (Integer n : numberList) {
                if (n<0) {
                    negativesList.add(n);
                } 
                if (n <= MAX_SUM_NUMBER) {
                    sum+=n;
                }
            }

            if (negativesList.isEmpty()) {
                return sum;
            } else {
                throw new InvalidParameterException("negatives not allowed " + negativesList.stream()
                                                                                    .map(Object::toString)
                                                                                    .collect(Collectors.joining(", ")));
            }
        }
    }

    public int getCalledCount() {
        return 0;
    }
}