package com.whizdm.payment_service.controller;


import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;

public interface ControllerService {
    public PaymentScheduleLos loanSaveSchedule (PaymentScheduleLos paymentSchedule);
    public UserEmiDetails loanPayEmi (UserEmiDetails details);
}
