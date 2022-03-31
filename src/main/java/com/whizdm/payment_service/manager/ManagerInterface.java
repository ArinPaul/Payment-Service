package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.customexceptions.InvalidDueAmount;
import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;

public interface ManagerInterface {

    public void disbursal(PaymentScheduleLos paymentScheduleLos);

    public int amountRoundOff(double amount);

    public void saveRepaymentSchedule(PaymentScheduleLos paymentScheduleLos);

    public boolean  dueAmountValidation(UserEmiDetails userEmiDetails);

    public void acceptPayment(UserEmiDetails userEmiDetails) throws InvalidDueAmount;

    public boolean check(String loanId);

    public String disbursePayment(int emi_amount, String payment_mode);

    String payment(UserEmiDetails emiDetails);
}
