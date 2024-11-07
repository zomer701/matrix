package com.matrix.numbers.matrix.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PythagoreanSquareUtils {

    public static Map<Integer, Integer> calculatePythagoreanSquare(String dateOfBirth) {
        Map<Integer, Integer> square = new HashMap<>();

        int sum1 = sumOfDigits(dateOfBirth);
        int sum2 = sumOfDigits(String.valueOf(sum1));
        int sum3 = sum1 - 2 * Character.getNumericValue(dateOfBirth.charAt(0));
        int sum4 = sumOfDigits(String.valueOf(sum3));

        String allNumbers = dateOfBirth + sum1 + sum2 + sum3 + sum4;

        for (char c : allNumbers.toCharArray()) {
            int digit = Character.getNumericValue(c);
            square.put(digit, square.getOrDefault(digit, 0) + 1);
        }

        return square;
    }

    private static int sumOfDigits(String num) {
        int sum = 0;
        for (char c : num.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
}
