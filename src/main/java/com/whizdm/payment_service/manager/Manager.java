package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.dao.DaoLoanDisbursal;
import com.whizdm.payment_service.dao.DaoLoanPayment;
import com.whizdm.payment_service.dao.DaoLoanPaymentSchedule;
import com.whizdm.payment_service.entity.LoanDisbursal;
import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class Manager implements ManagerInterface {


    private DaoLoanDisbursal daoLoanDisbursal;
    private DaoLoanPayment daoLoanPayment;
    private DaoLoanPaymentSchedule daoLoanPaymentSchedule ;

    Random random = new Random();

    @Autowired
    public Manager(DaoLoanDisbursal daoLoanDisbursal, DaoLoanPayment daoLoanPayment, DaoLoanPaymentSchedule daoLoanPaymentSchedule) {
        this.daoLoanDisbursal = daoLoanDisbursal;
        this.daoLoanPayment = daoLoanPayment;
        this.daoLoanPaymentSchedule = daoLoanPaymentSchedule;
    }

    @Override
    public void disbursal(PaymentScheduleLos paymentScheduleLos) {

        LoanDisbursal theLoanDisbursal = new LoanDisbursal(Long.parseLong(paymentScheduleLos.getLoan_id()),
                paymentScheduleLos.getPartner_id(),
                paymentScheduleLos.getBank_account_no(),
                paymentScheduleLos.getDisbursal_amount(),
                new java.util.Date(),
                Integer.toString(random.nextInt(99999999)));
        daoLoanDisbursal.saveLoanDisbursal(theLoanDisbursal);
    }

    @Override
    public int amountRoundOff(double amount) {
        return 0;
    }

    @Override
    public void saveRepaymentSchedule(PaymentScheduleLos paymentScheduleLos) {

        return;
    }

    @Override
    public boolean dueAmountValidation(UserEmiDetails userEmiDetails) {
        return false;
    }

    @Override
    public void acceptPayment(UserEmiDetails userEmiDetails) {

    }
}

