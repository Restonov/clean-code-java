package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {
    private static final int INCREASED_PERCENT_ACCOUNT_AGE = 60;
    private static final int NO_INTEREST_PERIOD_IN_YEARS = 13;
    private static final double REGULAR_PERCENT_VALUE = 4.5;
    private static final double INCREASED_PERCENT_VALUE = 5.5;
    private static final int LEAP_YEAR_SHIFT = 1;
    private static final int ONE_HUNDRED_PERCENTS = 100;

    @Override
    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        return isAccountCreatedAfterNoInterestPeriod(accountDetails)
                ? calculate(accountDetails)
                : BigDecimal.ZERO;
    }

    private boolean isAccountCreatedAfterNoInterestPeriod(AccountDetails accountDetails) {
        return differenceBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate())
                > NO_INTEREST_PERIOD_IN_YEARS;
    }

    private BigDecimal calculate(AccountDetails accountDetails) {
        double percentValue = definePercentValue(accountDetails);
        double interest = accountDetails.getBalance().doubleValue()
                * calculateAccountAge(accountDetails.getStartDate(), new Date())
                * percentValue / ONE_HUNDRED_PERCENTS;
        return BigDecimal.valueOf(interest);
    }

    private double definePercentValue(AccountDetails accountDetails) {
        int accountAge = calculateAccountAge(accountDetails.getStartDate(), new Date());
        return accountAge >= INCREASED_PERCENT_ACCOUNT_AGE
                ? INCREASED_PERCENT_VALUE
                : REGULAR_PERCENT_VALUE;
    }

    public int calculateAccountAge(Date accountCreationDate, Date currentDate) {
        return differenceBetweenDatesInYears(accountCreationDate, currentDate);
    }

    private int differenceBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = retrieveCalendar(from);
        Calendar endCalendar = retrieveCalendar(to);
        int difference = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (isLeapYearShiftRequired(startCalendar, endCalendar)) {
            difference--;
        }
        return difference;
    }

    private boolean isLeapYearShiftRequired(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private Calendar retrieveCalendar(Date time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        return calendar;
    }
}
