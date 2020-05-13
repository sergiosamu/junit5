package com.sergiosanchez.katas.tdd;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String NEW_LINE = "\n";
    private static final String NEGATIVE_VALUES_SEPARATOR = ", ";
    private static final String DELIMITER_PREFIX = "[";
    private static final String DELIMITER_SUFFIX = "]";
    private static final String SEPARATOR_INDICATOR = "//";
    private static final String REGEX_ALT = "|";
    private static final int MAX_SUM_NUMBER = 1000;
    private static final String DEFAULT_SEPARATOR = ",";

    private int calledCount;

    public int add(String numbers) {
        calledCount++;

        String value = numbers;
        
        if (numbers.equals("")) {
            return 0;
        } else {
            int sum = 0;

            String[] separatorList = new String[] { DEFAULT_SEPARATOR };

            if (value.startsWith(SEPARATOR_INDICATOR + DELIMITER_PREFIX)) {

                int separatorEndIndex = value.lastIndexOf(DELIMITER_SUFFIX);

                separatorList = value.substring((SEPARATOR_INDICATOR + DELIMITER_PREFIX).length(), separatorEndIndex)
                                     .split(Pattern.quote(DELIMITER_SUFFIX + DELIMITER_PREFIX));

                value = value.trim().substring(separatorEndIndex + 1);

            } else if (value.startsWith(SEPARATOR_INDICATOR)) {

                separatorList[0] = value.substring(SEPARATOR_INDICATOR.length(), SEPARATOR_INDICATOR.length() + 1);
                value = value.substring(3);

            }

            value = value.replace(NEW_LINE, separatorList[0]);

            String separatorPattern = Arrays.stream(separatorList)
                                            .map(s -> Pattern.quote(s))
                                            .collect(Collectors.joining(REGEX_ALT));

            List<Integer> numberList = Arrays.stream(value.split(separatorPattern)).filter(n -> !n.equals(""))
                                             .map(n -> Integer.valueOf(n)).collect(Collectors.toList());

            List<Integer> negativesList = new ArrayList<Integer>();

            for (Integer n : numberList) {
                if (n < 0) {
                    negativesList.add(n);
                }
                if (n <= MAX_SUM_NUMBER) {
                    sum += n;
                }
            }

            if (negativesList.isEmpty()) {
                return sum;
            } else {
                throw new InvalidParameterException("negatives not allowed "
                                                    + negativesList.stream()
                                                                   .map(Object::toString).collect(Collectors.joining(NEGATIVE_VALUES_SEPARATOR)));
            }
        }
    }

    public int getCalledCount() {
        return this.calledCount;
    }
}