package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import lombok.Data;

@Data
public abstract class UserController implements Controller {

    private UserAuthenticationService userAuthenticator;

    public void loginUser(String userName, String userPassword) {
        if (userAuthenticator.authenticate(userName, userPassword)) {
            generateSuccessLoginResponse(userName);
        } else {
            generateFailLoginResponse();
        }
    }
}
