package com.springrest.demo.RestController;

import com.springrest.demo.entity.loanPaymentSchedule;
import com.springrest.demo.entity.userEmiDetails;
import com.springrest.demo.utils.APICaller.APICaller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller implements ControllerService {
    APICaller call = APICaller.getInstance();
    @PostMapping("/payments/api/loanDisbursal")
    public loanPaymentSchedule loanSaveSchedule(loanPaymentSchedule paymentSchedule){
        //Save Repayment Schedule
        //Disburse Loan
        //Communication service API call to notify user
        return paymentSchedule;
    }

    @PostMapping(path = "/payments/api/emiPayment", consumes = "application/json")
    public userEmiDetails loanPayEmi(@RequestBody userEmiDetails emiDetails){
        //AuthToken Validation API Call
        //LOS API call to check if loan is open
        //Check database to verify due amount
        //Accept payment and call communication service API to notify use
        System.out.println(emiDetails);

        return emiDetails;
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
