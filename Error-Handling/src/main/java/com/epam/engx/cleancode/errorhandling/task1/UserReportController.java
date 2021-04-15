package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exception.DaoIsNullException;
import com.epam.engx.cleancode.errorhandling.task1.exception.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.exception.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;
import lombok.Getter;
import lombok.Setter;

public class UserReportController {
    private static final String USER_ID_NOT_EXIST = "WARNING: User ID doesn't exist.";
    private static final String WRONG_ORDER_AMOUNT = "ERROR: Wrong order amount.";
    private static final String USER_HAVE_NO_ORDERS = "WARNING: User have no submitted orders.";
    private static final String TECHNICAL_ERROR = "technicalError";
    private static final String USER_TOTAL_MESSAGE = "userTotalMessage";
    private static final String USER_TOTAL_VIEW = "userTotal";
    private static final String USER_TOTAL_ORDER_AMOUNT_USD = "User Total: %.2f$";
    public static final double NO_ORDERS_AMOUNT = 0.0;

    @Setter
    private UserReportBuilder userReportBuilder;

    public String retrieveUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage = retrieveUserTotalMessage(userId);
        model.addAttribute(USER_TOTAL_MESSAGE, totalMessage);
        return USER_TOTAL_VIEW;
    }

    private String retrieveUserTotalMessage(String userId) {
        String totalMessage;
        try {
            double totalOrderAmount = userReportBuilder.retrieveUserTotalOrderAmount(userId);
            if (totalOrderAmount == NO_ORDERS_AMOUNT) {
                totalMessage = USER_HAVE_NO_ORDERS;
            } else {
                totalMessage = String.format(USER_TOTAL_ORDER_AMOUNT_USD, totalOrderAmount);
            }
        } catch (UserNotFoundException e) {
            totalMessage = USER_ID_NOT_EXIST;
        } catch (WrongOrderAmountException e) {
            totalMessage = WRONG_ORDER_AMOUNT;
        } catch (DaoIsNullException e) {
            totalMessage = TECHNICAL_ERROR;
        }
        return totalMessage;
    }
}

