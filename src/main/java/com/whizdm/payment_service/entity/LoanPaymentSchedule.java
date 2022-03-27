package com.whizdm.payment_service.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loan_payment_schedule")
public class LoanPaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @Column(name = "loan_application_id")
    private long loanApplicationId ;

    @Column(name = "emi")
    private float emi ;

    @Column(name = "due_date")
    private Date dueDate ;

    @Column(name = "status")
    private String status;

    @Column(name = "due_amount")
    private float dueAmount ;

    @Column(name = "principle_amount")
    private float principleAmount ;

    @Column(name = "interest_amount")
    private float interestAmount ;

    public LoanPaymentSchedule(){

    }

    public LoanPaymentSchedule(long loanApplicationId, float emi, Date dueDate, String status, float dueAmount, float principleAmount, float interestAmount) {
        this.loanApplicationId = loanApplicationId;
        this.emi = emi;
        this.dueDate = dueDate;
        this.status = status;
        this.dueAmount = dueAmount;
        this.principleAmount = principleAmount;
        this.interestAmount = interestAmount;
    }

    //    getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "LoanPaymentSchedule{" +
                "id='" + id + '\'' +
                ", loanApplicationId=" + loanApplicationId +
                ", emi=" + emi +
                ", dueDate=" + dueDate +
                ", dueAmount=" + dueAmount +
                ", principleAmount=" + principleAmount +
                ", interestAmount=" + interestAmount +
                '}';
    }
}
