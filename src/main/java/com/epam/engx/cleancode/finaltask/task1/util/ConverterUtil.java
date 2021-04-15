package com.epam.engx.cleancode.finaltask.task1.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConverterUtil {

    public List<String> convertObjectsIntoStrings(List<Object> objects) {
        return objects.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
