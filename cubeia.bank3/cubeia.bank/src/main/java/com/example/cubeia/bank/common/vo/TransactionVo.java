package com.example.cubeia.bank.common.vo;

import java.math.BigDecimal;

public class TransactionVo {

    private String transactionId;

    private String transactionType;

    private String from;

    private String to;

    private BigDecimal amount;

    public TransactionVo(String transactionId, String transactionType, String from, String to, BigDecimal amount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public TransactionVo () {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
