package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

class FalseUserAuthenticator extends UserAuthenticationService {

    @Override
    public boolean isPasswordCorrect(String password) {
        return false;
    }

    @Override
    public User getUserByName(String userName) {
        return new User() {};
    }
}
