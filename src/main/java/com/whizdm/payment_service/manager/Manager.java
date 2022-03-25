package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.dao.DaoLoanDisbursal;
import com.whizdm.payment_service.dao.DaoLoanPayment;
import com.whizdm.payment_service.dao.DaoLoanPaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class Manager {


    private DaoLoanDisbursal daoLoanDisbursal;
    private DaoLoanPayment daoLoanPayment;
    private DaoLoanPaymentSchedule daoLoanPaymentSchedule ;


    public Manager(DaoLoanDisbursal daoLoanDisbursal, DaoLoanPayment daoLoanPayment, DaoLoanPaymentSchedule daoLoanPaymentSchedule) {
        this.daoLoanDisbursal = daoLoanDisbursal;
        this.daoLoanPayment = daoLoanPayment;
        this.daoLoanPaymentSchedule = daoLoanPaymentSchedule;
    }

}

