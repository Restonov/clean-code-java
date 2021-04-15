package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;
import lombok.Data;

@Data
public abstract class UserAuthenticationService implements UserService {

    private SessionManager sessionManager;

    public boolean authenticate(String userName, String password) {
        User user = getUserByName(userName);
        boolean authenticationResult = isUserCredentialsCorrect(user, password);
        if (authenticationResult) {
            attachUserToSession(user);
        }
        return authenticationResult;
    }

    private boolean isUserCredentialsCorrect(User user, String password) {
        return user != null && isPasswordCorrect(password);
    }

    private void attachUserToSession(User user) {
        sessionManager.setCurrentUser(user);
    }
}
