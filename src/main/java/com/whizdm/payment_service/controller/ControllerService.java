package com.whizdm.payment_service.controller


import com.springrest.demo.entity.loanPaymentSchedule;
import com.springrest.demo.entity.userEmiDetails;

public interface ControllerService {
    public loanPaymentSchedule loanSaveSchedule (loanPaymentSchedule paymentSchedule);
    public userEmiDetails loanPayEmi (userEmiDetails emiDetails);
}
