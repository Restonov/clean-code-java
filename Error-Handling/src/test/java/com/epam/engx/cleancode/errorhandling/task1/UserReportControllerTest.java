package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.stubs.*;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;

public class UserReportControllerTest {


    private static final String USER_ID = "123";
    private final List<Order> orders = new ArrayList<>(asList(new SubmittedOrderStub(), new AnotherSubmittedOrderStub(), new NotSubmittedOrderStub()));
    private UserReportController userReportController = new UserReportController();
    private final UserReportBuilder userReportBuilder = new UserReportBuilder();

    @Before
    public void setUp() {
        userReportController.setUserReportBuilder(userReportBuilder);
        userReportBuilder.setUserDao(userId -> {
            if (isNotExistUser(userId))
                return null;
            return () -> orders;
        });
    }

    private boolean isNotExistUser(String userId) {
        return !Objects.equals(userId, USER_ID);
    }

    @Test
    public void shouldCalculateSumOfAllSubmittedOrders() {

        Model model = new ModelStub();
        String amount = userReportController.retrieveUserTotalOrderAmountView("123", model);

        Assert.assertEquals("userTotal", amount);
        Assert.assertEquals("User Total: 363.15$", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldGetWarningMessageWhenUserDoesntExist() {
        Model model = new ModelStub();
        String amount = userReportController.retrieveUserTotalOrderAmountView("0001", model);

        Assert.assertEquals("userTotal", amount);
        Assert.assertEquals("WARNING: User ID doesn't exist.", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldGetErrorMessageWhenOrderHaveNegativeAmount() {

        orders.add(new SubmittedNegativeOrderStub());

        Model model = new ModelStub();
        String amount = userReportController.retrieveUserTotalOrderAmountView("123", model);

        Assert.assertEquals("userTotal", amount);
        Assert.assertEquals("ERROR: Wrong order amount.", model.getAttribute("userTotalMessage"));
    }


    @Test
    public void shouldGetWarningMessageWhenUserHaveNoSubmittedOrders() {

        orders.clear();

        Model model = new ModelStub();
        String amount = userReportController.retrieveUserTotalOrderAmountView("123", model);

        Assert.assertEquals("userTotal", amount);
        Assert.assertEquals("WARNING: User have no submitted orders.", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldRedirectToErrorPageWhenConnectionToDbIsNull() {

        Model model = new ModelStub();
        userReportBuilder.setUserDao(null);

        String amount = userReportController.retrieveUserTotalOrderAmountView("123", model);

        Assert.assertEquals("technicalError", model.getAttribute("userTotalMessage"));
    }
}
