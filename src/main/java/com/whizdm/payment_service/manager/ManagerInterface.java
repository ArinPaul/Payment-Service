package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;

public interface ManagerInterface {

    public void disbursal(PaymentScheduleLos paymentScheduleLos);

    public int amountRoundOff(double amount);

    public void saveRepaymentSchedule(PaymentScheduleLos paymentScheduleLos);

    public boolean  dueAmountValidation(UserEmiDetails userEmiDetails);

    public void acceptPayment(UserEmiDetails userEmiDetails);

    public boolean check(String loanId);
}
