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
    private String loanApplicationId ;

    @Column(name = "emi")
    private int emi ;

    @Column(name = "due_date")
    private Date dueDate ;

    @Column(name = "payment_status")
    private String status;

    @Column(name = "due_amount")
    private int dueAmount ;

    @Column(name = "principle_amount")
    private float principleAmount ;

    @Column(name = "interest_amount")
    private float interestAmount ;

    public LoanPaymentSchedule(){

    }

    public LoanPaymentSchedule(String loanApplicationId, int emi, Date dueDate, String status, int dueAmount, float principleAmount, float interestAmount) {
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

    public String getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(String loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public int getEmi() {
        return emi;
    }

    public void setEmi(int emi) {
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

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
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
