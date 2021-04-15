package com.epam.engx.cleancode.finaltask.task1.util;

import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;

@UtilityClass
public class CalculatorUtil {
    private static final int EVEN_NUMBER_DIVIDER = 2;

    public int calculateTheLongestText(List<String> texts) {
        return texts.stream()
                .map(String::length)
                .max(Comparator.comparingInt(Integer::intValue))
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isNumberEven(int number) {
        return number % EVEN_NUMBER_DIVIDER == 0;
    }
}
