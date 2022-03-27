package com.whizdm.payment_service.controller;

import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import com.whizdm.payment_service.manager.Manager;
import com.whizdm.payment_service.utils.APICaller.APICaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller implements ControllerService {


    private Manager manager;
    private APICaller caller;

    @Autowired
    public Controller(Manager theManager){
        this.manager = theManager;
        this.caller = APICaller.getInstance();
    }

    @PostMapping("/payments/api/loanDisbursal")
    public ResponseEntity<PaymentScheduleLos> loanSaveSchedule(PaymentScheduleLos paymentSchedule){
        //Save Repayment Schedule
        manager.saveRepaymentSchedule(paymentSchedule);
        //Disburse Loan
        //Communication service API call to notify user
//        try{
//            caller.APICall(""); //Communication Service API EndPoint
//        }catch(Exception e){
//
//        }
        return new ResponseEntity<PaymentScheduleLos>(paymentSchedule,HttpStatus.OK);
    }

    @PostMapping(path = "/payments/api/emiPayment", consumes = "application/json")
    public ResponseEntity<UserEmiDetails> loanPayEmi(@RequestBody UserEmiDetails emiDetails){
        //AuthToken Validation API Call
        //LOS API call to check if loan is open
        //Check database to verify due amount
        //Accept payment and call communication service API to notify use

//        try{
//            caller.APICall(""); //Communication Service API EndPoint
//        }catch(Exception e){
//
//        }

        return new ResponseEntity<UserEmiDetails>(emiDetails,HttpStatus.OK);
    }






//    @GetMapping("/payments/api/LoanDisbursal")
//    public String demo(){
//
//        var url = "https://covid19.mathdro.id/api";
//        String res = "";
//        try{
//           res = call.APICall(url);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }finally {
//            return res;
//        }
//    }




}
