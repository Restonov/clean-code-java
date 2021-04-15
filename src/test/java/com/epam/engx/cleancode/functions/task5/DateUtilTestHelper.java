package com.epam.engx.cleancode.functions.task5;

import java.util.Date;

public class DateUtilTestHelper {

    public static Date getDirectlyIncrementedDate(Date date) {
        return DateUtil.incrementDate(date);
    }

    public static Date getInverseIncrementedDate(Date date) {
        return DateUtil.decrementDate(date);
    }
}
