package com.whizdm.payment_service.entity;

import java.util.Date;

public class LoanDisbursal {

    private String id;
    private String loanApplicationId;
    private int partnerId;
    private long bankAccountNumber;
    private int amount;
    private Date disbursalDate;
    private String disbursalUtrId;

    public LoanDisbursal(String loanApplicationId, int partnerId, long bankAccountNumber, int amount, Date disbursalDate, String disbursalUtrId) {
        this.loanApplicationId = loanApplicationId;
        this.partnerId = partnerId;
        this.bankAccountNumber = bankAccountNumber;
        this.amount = amount;
        this.disbursalDate = disbursalDate;
        this.disbursalUtrId = disbursalUtrId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(String loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDisbursalDate() {
        return disbursalDate;
    }

    public void setDisbursalDate(Date disbursalDate) {
        this.disbursalDate = disbursalDate;
    }

    public String getDisbursalUtrId() {
        return disbursalUtrId;
    }

    public void setDisbursalUtrId(String disbursalUtrId) {
        this.disbursalUtrId = disbursalUtrId;
    }
}
