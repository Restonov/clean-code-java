package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.WRONG;

@Data
public class AccountRegistration {
    private static final int VALID_ACCOUNT_NAME_LENGTH = 5;
    private static final int VALID_ACCOUNT_PASSWORD_LENGTH = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccount(account);
        manageAccountAddresses(account);
        account.setCreatedDate(new Date());
        accountManager.createNewAccount(account);
    }

    private void validateAccount(Account account) {
        validateAccountName(account.getName());
        validateAccountPassword(account.getPassword());
    }

    private void validateAccountName(String accountName) {
        if (accountName.length() <= VALID_ACCOUNT_NAME_LENGTH) {
            throw new WrongAccountNameException();
        }
    }

    private void validateAccountPassword(String accountPassword) {
        if (accountPassword.length() <= VALID_ACCOUNT_PASSWORD_LENGTH
                || passwordChecker.validate(accountPassword) == WRONG) {
            throw new WrongPasswordException();
        }
    }

    private void manageAccountAddresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }
}
