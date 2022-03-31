package com.whizdm.payment_service.controller;

import com.whizdm.payment_service.entity.PaymentScheduleLos;
import com.whizdm.payment_service.entity.UserEmiDetails;
import com.whizdm.payment_service.manager.Manager;
import com.whizdm.payment_service.utils.APICaller.APICaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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
            caller.postAPICallComm("","SD",paymentSchedule.getUser_id(),paymentSchedule.getLoan_id(),paymentSchedule.getBank_account_no(),Double.toString(paymentSchedule.getPrincipal_amount())); //Communication Service API EndPoint
        }catch(Exception e){
            System.out.println("Communication API for Loan Disbursal Failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Process Completed Successfully",HttpStatus.OK);
    }



    @PostMapping(path = "/emiPayment", consumes = "application/json")
    public ResponseEntity<String> loanPayEmi(@RequestBody UserEmiDetails emiDetails) throws IOException, InterruptedException {
        //AuthToken Validation API Call
        boolean valid = true;
//        try{
//            valid = Boolean.parseBoolean(caller.postAPICall("",emiDetails.getAuth_token(),"Auth Token Verification")); //Auth Service API EndPoint
//        }catch(Exception e){
//            System.out.println("Auth service API call failed");
//            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        if(!valid){
            System.out.println("Authentication Failed");
            return new ResponseEntity<String>("Authentication Failed",HttpStatus.FORBIDDEN);
        }

        //LOS API call to check if loan is open
        boolean res = true;
//        try{
//            res = Boolean.parseBoolean(caller.postAPICall("",emiDetails.getLoan_id(),"LoanVerification")); //LOS Service API EndPoint
//        }catch(Exception e){
//            System.out.println("LOS Service API Call failed for validation");
//            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        if(!res){
            caller.postAPICallComm("","MEMIF",emiDetails.getUser_id(),emiDetails.getLoan_id(),"","Loan Application Closed");
            System.out.println("Loan Application Closed");
            return new ResponseEntity<String>("Loan Application Closed",HttpStatus.NOT_ACCEPTABLE);
        }

        //Accept payment if amount is valid
        try{
            manager.acceptPayment(emiDetails);
        }catch (Exception InvalidDueAmount) {
            System.out.println("Payment Acceptance Failed");
            //caller.postAPICallComm("","MEMIF",emiDetails.getUser_id(),emiDetails.getLoan_id(),"",InvalidDueAmount.getMessage());
            return new ResponseEntity<String>(InvalidDueAmount.getMessage(), HttpStatus.BAD_REQUEST);
        }



        //Communication service API call to notify user
        try{
            caller.postAPICallComm("","MEMIS",emiDetails.getUser_id(),emiDetails.getLoan_id(),"","EMI Payment Processed Successfully");//Communication Service API EndPoint
        }catch(Exception e){
            System.out.println("Communication service API call failed");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(manager.check(emiDetails.getLoan_id())){
            caller.postAPICall("",emiDetails.getLoan_id(),"CloseApplication");
        }

        return new ResponseEntity<String>("EMI Payment Successfully Completed",HttpStatus.OK);
    }

    //Payment Receiving
    @GetMapping("/receivePayment")
    public ResponseEntity<String> sendPayment(@RequestParam("amount") int amount, @RequestParam("method") String method){
        try {
            manager.disbursePayment(amount,method);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Payment Received",HttpStatus.OK);
    }

}

