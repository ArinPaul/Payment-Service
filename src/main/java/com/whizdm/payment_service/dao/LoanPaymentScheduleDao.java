package com.whizdm.payment_service.dao;

import com.whizdm.payment_service.entity.LoanPaymentSchedule;

import java.util.List;

public interface LoanPaymentScheduleDao {

    public void saveInitialSchedule(LoanPaymentSchedule loanPaymentSchedule);

    public void updateLoanPaymentSchedule();

    public List<LoanPaymentSchedule> retrieveLoanPayment(long theLoanApplicationId);
}
