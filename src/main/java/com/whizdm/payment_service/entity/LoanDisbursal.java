package com.whizdm.payment_service.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="loan_disbursal")
public class LoanDisbursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "partner_id")
    private int partnerId;

    @Column(name = "bank_account_number")
    private long bankAccountNumber;

    @Column(name = "amount")
    private int amount;

    @Column(name = "disbursal_date")
    private Date disbursalDate;

    @Column(name = "disbursal_utr_id")
    private String disbursalUtrId;

    public LoanDisbursal(){

    }

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

    @Override
    public String toString() {
        return "LoanDisbursal{" +
                "id='" + id + '\'' +
                ", loanApplicationId='" + loanApplicationId + '\'' +
                ", partnerId=" + partnerId +
                ", bankAccountNumber=" + bankAccountNumber +
                ", amount=" + amount +
                ", disbursalDate=" + disbursalDate +
                ", disbursalUtrId='" + disbursalUtrId + '\'' +
                '}';
    }
}
