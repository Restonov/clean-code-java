package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exception.DaoIsNullException;
import com.epam.engx.cleancode.errorhandling.task1.exception.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.exception.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;
import lombok.Data;
import lombok.Setter;

import java.util.List;

import static com.epam.engx.cleancode.errorhandling.task1.UserReportController.NO_ORDERS_AMOUNT;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;

public class UserReportBuilder {

    @Setter
    private UserDao userDao;

    public Double retrieveUserTotalOrderAmount(String userId) throws UserNotFoundException, WrongOrderAmountException {
        verifyDaoIsNotNull(userDao);
        User user = userDao.getUser(userId);
        verifyUserIsNotNull(user);
        List<Order> orders = user.getAllOrders();
        return calculateOrderAmount(orders);
    }

    private void verifyDaoIsNotNull(UserDao userDao) {
        if (userDao == null) {
            throw new DaoIsNullException("Dao is not initialized");
        }
    }

    private void verifyUserIsNotNull(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
    }

    private double calculateOrderAmount(List<Order> orders) throws WrongOrderAmountException {
        double orderAmount = NO_ORDERS_AMOUNT;
        if (isOrdersNotEmpty(orders)) {
            orderAmount = orders.stream()
                    .filter(this::isOrderVerified)
                    .mapToDouble(Order::total)
                    .sum();
        }
        return orderAmount;
    }

    private boolean isOrdersNotEmpty(List<Order> orders) {
        return !orders.isEmpty();
    }

    private boolean isOrderVerified(Order order) throws WrongOrderAmountException {
        boolean isVerified = false;
        if (order.isSubmitted()) {
            verifyRightOrderAmount(order);
            isVerified = true;
        }
        return isVerified;
    }

    private void verifyRightOrderAmount(Order order) throws WrongOrderAmountException {
        if (order.total() < INTEGER_ZERO) {
            throw new WrongOrderAmountException();
        }
    }
}
