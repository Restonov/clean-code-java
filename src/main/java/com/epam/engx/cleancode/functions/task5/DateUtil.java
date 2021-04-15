package com.epam.engx.cleancode.functions.task5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    private static final int INCREMENT_INT_ONE = 1;
    private static final int DECREMENT_INT_ONE = -1;

    public static Date incrementDate(Date date) {
        Calendar calendar = retrieveCalendar(date);
        calendar.add(Calendar.DAY_OF_MONTH, INCREMENT_INT_ONE);
        return calendar.getTime();
    }

    public static Date decrementDate(Date date) {
        Calendar calendar = retrieveCalendar(date);
        calendar.add(Calendar.DAY_OF_MONTH, DECREMENT_INT_ONE);
        return calendar.getTime();
    }

    private static Calendar retrieveCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        resetToMidnight(calendar);
        return calendar;
    }

    private static void resetToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
