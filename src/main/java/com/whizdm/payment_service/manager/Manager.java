package com.whizdm.payment_service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Manager {

//    import after Doa
    private DaoLoanDisbursal daoLoanDisbursal;
    private DaoLoanPayment daoLoanPayment;
    private DaoLoanPaymentSchedule daoLoanPaymentSchedule ;

    @Autowired
    public Manager(DaoLoanDisbursal daoLoanDisbursal, DaoLoanPayment daoLoanPayment, DaoLoanPaymentSchedule daoLoanPaymentSchedule) {
        this.daoLoanDisbursal = daoLoanDisbursal;
        this.daoLoanPayment = daoLoanPayment;
        this.daoLoanPaymentSchedule = daoLoanPaymentSchedule;
    }

    //    disbursal
    public void disbursal(){

    //       pass  LoanPaymentSchdule object after getting from API

    }



    //    amountRoundoff

    //    saveRepaymentSchedule
    //    dueAmountValidation

    public boolean dueAmountValidation(){

    }
    //    acceptpayment

    public void acceptpayment(){

    }
}

