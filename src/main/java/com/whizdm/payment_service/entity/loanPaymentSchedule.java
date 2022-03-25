package com.springrest.demo.entity;

import java.util.Date;

public class loanPaymentSchedule {
    private String loan_id;
    private long user_id;
    private double disbursal_amount;
    private double first_emi;
    private double last_emi;
    private double principal_amount;
    private float interest_component;
    private Date due_date;
    private int partner_id;
    private long bank_account_no;

}
