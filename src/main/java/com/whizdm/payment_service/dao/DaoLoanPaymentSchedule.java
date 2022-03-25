package com.whizdm.payment_service.dao;

import com.whizdm.payment_service.entity.LoanPaymentSchedule;

public interface DaoLoanPaymentSchedule {

    public void saveInitialSchedule(LoanPaymentSchedule loanPaymentSchedule);

    public void updateLoanPaymentSchedule();

    public void retrieveLoanPayment();
}
