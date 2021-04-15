package com.epam.engx.cleancode.functions.task2.stubs;

import com.epam.engx.cleancode.functions.task2.UserAccount;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;

import java.util.List;

public class AccountStub extends UserAccount {

    private List<Review> reviews;

    public AccountStub(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean isRegistered() {
        return true;
    }

    @Override
    public int getVisitNumber() {
        return 1;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviews;
    }
}
