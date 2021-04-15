package com.epam.engx.cleancode.naming.task3;

import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

public class HarshadNumbersGenerator {
    private static final long SEQUENCE_LIMIT = 200;
    private static final int BASE_TEN = 10;

    public String generateSequence() {
        StringBuilder numbers = new StringBuilder();
        for (int i = INTEGER_ONE; i <= SEQUENCE_LIMIT; i++) {
            if (i % generateNumber(i) == INTEGER_ZERO) {
                numbers.append(i).append("\n");
            }
        }
        return numbers.toString();
    }

    private int generateNumber(int counter) {
        int number = INTEGER_ZERO;
        while (counter != INTEGER_ZERO) {
            number += counter % BASE_TEN;
            counter = counter / BASE_TEN;
        }
        return number != INTEGER_ZERO ? number : INTEGER_ONE;
    }
}
