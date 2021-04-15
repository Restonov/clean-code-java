package com.epam.engx.cleancode.functions.task2.stubs;

import com.epam.engx.cleancode.functions.task2.UserAccount;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;

import java.util.List;

public class NotRegisteredAccountStub extends UserAccount {
    @Override
    public boolean isRegistered() {
        return false;
    }

    @Override
    public int getVisitNumber() {
        return 1;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }
}
