package com.whizdm.payment_service.controller;

import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import com.whizdm.payment_service.manager.Manager;
import com.whizdm.payment_service.utils.APICaller.APICaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments/api")
public class Controller implements ControllerService {


    private Manager manager;
    private APICaller caller;

    @Autowired
    public Controller(Manager theManager){
        this.manager = theManager;
        this.caller = APICaller.getInstance();
    }

    @PostMapping("/loanDisbursal")
    public ResponseEntity<String> loanSaveSchedule(@RequestBody PaymentScheduleLos paymentSchedule) {
        //Save Repayment Schedule
        try {
            manager.saveRepaymentSchedule(paymentSchedule);
            System.out.println("Saved");
        }catch (Exception e){
            System.out.println("Saving Failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Disburse Loan
        try {
            manager.disbursal(paymentSchedule);
        }catch (Exception e){
            System.out.println("Loan Disbursal Failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Communication service API call to notify user

        try{
            caller.postAPICall("",paymentSchedule.getUser_id(),"Loan amount disbursed successfully"); //Communication Service API EndPoint
        }catch(Exception e){
            System.out.println("Communication API for Loan Disbursal Failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        try{
//            caller.APICall(""); //Communication Service API EndPoint
//        }catch(Exception e){
//            System.out.println("Communication API for Loan Disbursal Failed");
//            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return new ResponseEntity<String>("Process Completed Successfully",HttpStatus.OK);
    }



    @PostMapping(path = "/emiPayment", consumes = "application/json")
    public ResponseEntity<String> loanPayEmi(@RequestBody UserEmiDetails emiDetails){
        //AuthToken Validation API Call
        try{
            var res = caller.postAPICall("",emiDetails.getAuth_token(),""); //Auth Service API EndPoint
        }catch(Exception e){
            System.out.println("Auth service API call failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //LOS API call to check if loan is open
        try{
            var res = caller.postAPICall("",emiDetails.getLoan_id(),"Loan Verification for payment"); //LOS Service API EndPoint
        }catch(Exception e){
            System.out.println("LOS Service API Call failed for validation");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        //Check database to verify due amount
//        boolean validAmount = false;
//        try {
//            validAmount = manager.dueAmountValidation(emiDetails);
//        }catch (Exception e){
//            System.out.println("Due amount validation failed");
//            return new ResponseEntity<String>("Couldn't verify the due amount.",HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        //Accept payment if amount is valid
        try{
            manager.acceptPayment(emiDetails);
        }catch (Exception InvalidDueAmount) {
            System.out.println("Payment Acceptance Failed");
            return new ResponseEntity<String>("Invalid Due Amount. Please enter a valid amount.", HttpStatus.BAD_REQUEST);
        }



        //Communication service API call to notify user
        try{
            caller.postAPICall("",emiDetails.getUser_id()," EMI Payment Processed Successfully"); //Communication Service API EndPoint
        }catch(Exception e){
            System.out.println("Communication service API call failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(manager.check(emiDetails.getLoan_id())){
            //Call LOS to close application
        }



        return new ResponseEntity<String>("EMI Payment Successfully Completed",HttpStatus.OK);
    }




//    @GetMapping("/LoanDisbursal")
//    public String demo(){
//
//        var url = "https://covid19.mathdro.id/api";
//        String res = "";
//        try{
//           res = caller.APICall(url);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }finally {
//            return res;
//        }
//    }




}

