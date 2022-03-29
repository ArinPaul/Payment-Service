package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.dao.LoanDisbursalDao;
import com.whizdm.payment_service.dao.LoanPaymentDao;
import com.whizdm.payment_service.dao.LoanPaymentScheduleDao;
import com.whizdm.payment_service.entity.LoanDisbursal;
import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class Manager implements ManagerInterface {


    private LoanDisbursalDao loanDisbursalDao;
    private LoanPaymentDao loanPaymentDao;
    private LoanPaymentScheduleDao loanPaymentScheduleDao;

    Random random = new Random();

    @Autowired
    public Manager(LoanDisbursalDao loanDisbursalDao, LoanPaymentDao loanPaymentDao, LoanPaymentScheduleDao loanPaymentScheduleDao) {
        this.loanDisbursalDao = loanDisbursalDao;
        this.loanPaymentDao = loanPaymentDao;
        this.loanPaymentScheduleDao = loanPaymentScheduleDao;
    }

    @Override
    public void disbursal(PaymentScheduleLos paymentScheduleLos) {

        LoanDisbursal theLoanDisbursal = new LoanDisbursal(paymentScheduleLos.getLoan_id(),
                paymentScheduleLos.getPartner_id(),
                paymentScheduleLos.getBank_account_no(),
                (int)paymentScheduleLos.getDisbursal_amount(),
                new java.util.Date(),
                Integer.toString(random.nextInt(99999999)));
        loanDisbursalDao.saveLoanDisbursal(theLoanDisbursal);
    }

    @Override
    public int amountRoundOff(double amount) {
        int ans = (int)(amount*12) - (int)(Math.round(amount)*11);
        return ans;
    }

    @Override
    public void saveRepaymentSchedule(PaymentScheduleLos paymentScheduleLos) {
    }

    @Override
    public boolean dueAmountValidation(UserEmiDetails userEmiDetails) {
        double enteredAmount = userEmiDetails.getEmi_amount();
        return false;
    }

    @Override
    public void acceptPayment(UserEmiDetails userEmiDetails) {
    }
}

