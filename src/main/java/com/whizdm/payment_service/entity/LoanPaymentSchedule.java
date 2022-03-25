package com.whizdm.payment_service.entity;

import java.util.Date;

public class LoanPaymentSchedule {

    private String id ;
    private long loanApplicationId ;
    private float emi ;
    private Date dueDate ;
    private float dueAmount ;
    private float principleAmount ;
    private float interestAmount ;

    public LoanPaymentSchedule(long loanApplicationId, float emi, Date dueDate, float dueAmount, float principleAmount, float interestAmount) {
        this.loanApplicationId = loanApplicationId;
        this.emi = emi;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
        this.principleAmount = principleAmount;
        this.interestAmount = interestAmount;
    }

    //    getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public float getEmi() {
        return emi;
    }

    public void setEmi(float emi) {
        this.emi = emi;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public float getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(float dueAmount) {
        this.dueAmount = dueAmount;
    }

    public float getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(float principleAmount) {
        this.principleAmount = principleAmount;
    }

    public float getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(float interestAmount) {
        this.interestAmount = interestAmount;
    }
}
