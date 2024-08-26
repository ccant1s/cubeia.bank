package com.example.cubeia.bank.common.vo;

import java.math.BigDecimal;

public class UserVo {

    private String accountId;
    private String lastName;
    private String firstName;
    private BigDecimal accountBalance;
    private String lastUpdatedTime;

    public UserVo(String accountId, String lastName, String firstName, BigDecimal accountBalance, String lastUpdatedTime) {
        this.accountId = accountId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.accountBalance = accountBalance;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public UserVo (){

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
