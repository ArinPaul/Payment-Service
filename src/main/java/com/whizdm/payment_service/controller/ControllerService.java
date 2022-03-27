package com.whizdm.payment_service.controller;


import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

public interface ControllerService {
    public ResponseEntity<PaymentScheduleLos> loanSaveSchedule (PaymentScheduleLos paymentSchedule);
    public ResponseEntity<UserEmiDetails> loanPayEmi (UserEmiDetails details);
}
