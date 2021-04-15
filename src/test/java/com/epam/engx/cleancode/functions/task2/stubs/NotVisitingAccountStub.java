package com.epam.engx.cleancode.functions.task2.stubs;

import com.epam.engx.cleancode.functions.task2.UserAccount;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;

import java.util.List;

public class NotVisitingAccountStub extends UserAccount {
    @Override
    public boolean isRegistered() {
        return true;
    }

    @Override
    public int getVisitNumber() {
        return 0;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }
}
