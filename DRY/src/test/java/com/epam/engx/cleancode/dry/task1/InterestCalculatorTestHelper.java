package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Account;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class InterestCalculatorTestHelper {

    public static Account makeAccountDetails(double balance, int age, int startedYearsAgo) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setPrincipalAmount(new BigDecimal(balance));
        accountDetails.setClientBirthDate(getCurrentDateMinusYears(age));
        accountDetails.setStartDate(getCurrentDateMinusYears(startedYearsAgo));
        return accountDetails;
    }

    private static Date getCurrentDateMinusYears(int years) {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) - years);
        return now.getTime();
    }
}
