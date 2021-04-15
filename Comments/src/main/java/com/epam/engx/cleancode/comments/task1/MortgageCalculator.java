package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;
import lombok.experimental.UtilityClass;

import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

@UtilityClass
public class MortgageCalculator {
    private final double MONTHS_AMOUNT = 12.0;
    private final double ZERO_INTEREST_RATE = 0.0;
    private final double ONE_HUNDRED_PERCENTS = 100.0;

    public double calculateMonthlyPayment(int principalAmount, int termInYears, double annualInterestRate) {
        verifyValuesIsNotNegative(principalAmount, termInYears, annualInterestRate);
        double termInMonths = convertYearsIntoMonths(termInYears);
        double monthlyPayment = principalAmount / termInMonths;
        if (annualInterestRate > ZERO_INTEREST_RATE) {
            double monthlyInterestRate = calculateMonthlyInterestRate(annualInterestRate);
            monthlyPayment = calculatePayment(principalAmount, termInMonths, monthlyInterestRate);
        }
        return monthlyPayment;
    }

    private void verifyValuesIsNotNegative(int principalAmount, int lengthInYears, double interestRate) {
        if (principalAmount < INTEGER_ZERO || lengthInYears <= INTEGER_ZERO || interestRate < INTEGER_ZERO) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }

    private double convertYearsIntoMonths(int years) {
        return years * MONTHS_AMOUNT;
    }

    private double calculateMonthlyInterestRate(double annualInterestRate) {
        return shiftDecimalPointTwoPlacesLeft(annualInterestRate) / MONTHS_AMOUNT;
    }

    private double shiftDecimalPointTwoPlacesLeft(double interestRate) {
        return interestRate / ONE_HUNDRED_PERCENTS;
    }

    private double calculatePayment(int principalAmount, double lengthInMonths, double monthlyInterestRate) {
        return (principalAmount * monthlyInterestRate)
                / (INTEGER_ONE - Math.pow(INTEGER_ONE + monthlyInterestRate, -lengthInMonths));
    }
}
