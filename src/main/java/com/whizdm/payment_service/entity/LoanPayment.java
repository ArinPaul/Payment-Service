package com.whizdm.payment_service.entity;

import java.util.Date;

public class LoanPayment {

    private String id;
    private String loanApplicationId;
    private int paidAmount;
    private String paymentUtrId;
    private String paymentMode;
    private Date paymentDate;
    private String paymentStatus;
    private String failureReason;

    public LoanPayment(String loanApplicationId, int paidAmount, String paymentUtrId, String paymentMode, Date paymentDate, String paymentStatus, String failureReason) {
        this.loanApplicationId = loanApplicationId;
        this.paidAmount = paidAmount;
        this.paymentUtrId = paymentUtrId;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.failureReason = failureReason;
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

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentUtrId() {
        return paymentUtrId;
    }

    public void setPaymentUtrId(String paymentUtrId) {
        this.paymentUtrId = paymentUtrId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
