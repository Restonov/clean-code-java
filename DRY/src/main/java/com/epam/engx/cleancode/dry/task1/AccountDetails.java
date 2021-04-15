package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountDetails implements Account {
    private Date clientBirthDate;
    private BigDecimal principalAmount;
    private Date creationDate;

    @Override
    public Date getBirth() {
        return clientBirthDate;
    }

    @Override
    public Date getStartDate() {
        return creationDate;
    }

    @Override
    public BigDecimal getBalance() {
        return principalAmount;
    }

    @Override
    public void setStartDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
