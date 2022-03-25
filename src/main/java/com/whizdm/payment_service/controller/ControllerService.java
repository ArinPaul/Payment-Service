package com.springrest.demo.RestController;


import com.springrest.demo.entity.loanPaymentSchedule;
import com.springrest.demo.entity.userEmiDetails;

public interface ControllerService {
    public loanPaymentSchedule loanSaveSchedule (loanPaymentSchedule paymentSchedule);
    public userEmiDetails loanPayEmi (userEmiDetails emiDetails);
}
