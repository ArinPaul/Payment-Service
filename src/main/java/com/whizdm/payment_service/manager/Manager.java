package com.whizdm.payment_service.manager;

import com.whizdm.payment_service.customexceptions.InvalidDueAmount;
import com.whizdm.payment_service.dao.LoanDisbursalDao;
import com.whizdm.payment_service.dao.LoanPaymentDao;
import com.whizdm.payment_service.dao.LoanPaymentScheduleDao;
import com.whizdm.payment_service.entity.*;
import com.whizdm.payment_service.utility.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class Manager implements ManagerInterface {


    private LoanDisbursalDao loanDisbursalDao;
    private LoanPaymentDao loanPaymentDao;
    private LoanPaymentScheduleDao loanPaymentScheduleDao;

    Random random = new Random();

    @Autowired
    public Manager(LoanDisbursalDao loanDisbursalDao, LoanPaymentDao loanPaymentDao, LoanPaymentScheduleDao loanPaymentScheduleDao) {
        this.loanDisbursalDao = loanDisbursalDao;
        this.loanPaymentDao = loanPaymentDao;
        this.loanPaymentScheduleDao = loanPaymentScheduleDao;
    }

    @Override
    public void disbursal(PaymentScheduleLos paymentScheduleLos) {

        LoanDisbursal loanDisbursal = new LoanDisbursal(
                paymentScheduleLos.getLoan_id(),
                paymentScheduleLos.getPartner_id(),
                paymentScheduleLos.getBank_account_no(),
                (int)paymentScheduleLos.getDisbursal_amount(),
                new Date(),
                StringRandom.get(),
                new Date(),
                new Date());
        loanDisbursalDao.saveLoanDisbursal(loanDisbursal);
    }

    @Override
    public int amountRoundOff(double amount) {
        int ans = (int)(amount*12) - (int)(Math.round(amount)*11);
        return ans;
    }

    @Override
    public void saveRepaymentSchedule(PaymentScheduleLos paymentScheduleLos) {
        //create loan_repayment_schedule
        List<LoanPaymentSchedule> list = new ArrayList<>();
        Date tmp = paymentScheduleLos.getDue_date();
        for(int i=0;i<12;i++){
            LoanPaymentSchedule theLoanPaymentSchedule = new LoanPaymentSchedule(
                    paymentScheduleLos.getLoan_id(),
                    (int)((i==11)?paymentScheduleLos.getLast_emi():paymentScheduleLos.getFirst_emi()),
                    tmp,
                    (int)((i==11)?paymentScheduleLos.getLast_emi():paymentScheduleLos.getFirst_emi()),
                    (float) paymentScheduleLos.getPrincipal_amount(),
                    paymentScheduleLos.getInterest_component(),
                    new Date(),
                    new Date()
            );

            list.add(theLoanPaymentSchedule);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tmp);
            calendar.add(calendar.MONTH,1);
            tmp = calendar.getTime();
        }

        loanPaymentScheduleDao.saveInitialSchedule(list);
    }

    @Override
    public boolean dueAmountValidation(UserEmiDetails userEmiDetails) {
        double enteredAmount = userEmiDetails.getEmi_amount();
        List<LoanPaymentSchedule> list = loanPaymentScheduleDao.retrieveLoanPayment(userEmiDetails.getLoan_id());

        LocalDate presentDate = LocalDate.now();
        presentDate = presentDate.plusMonths(1);

        int totalDueAmount = 0;
        int emi = list.get(0).getEmi();

        for(LoanPaymentSchedule val : list){
            LocalDate due = val.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(due.compareTo(presentDate) < 0){
                totalDueAmount += val.getDueAmount();
            }
        }

        if(userEmiDetails.getEmi_amount() == totalDueAmount || userEmiDetails.getEmi_amount() == emi){
            return true;
        }
        else{
            return false;
        }
    }



    @Override
    public void acceptPayment(UserEmiDetails userEmiDetails) {
        if(dueAmountValidation(userEmiDetails)) {
            //make entry in loan payment with status = success
            String utr = StringRandom.get();
            LoanPayment loanPayment = new LoanPayment(
                    userEmiDetails.getLoan_id(),
                    userEmiDetails.getEmi_amount(),
                    utr,
                    userEmiDetails.getPayment_mode(),
                    new Date(),
                    "Success",
                    "Not Applicable",
                    new Date(),
                    new Date());
            loanPaymentDao.saveLoanPayment(loanPayment);

            //update loan_payment_schedule
            List<LoanPaymentSchedule> list = loanPaymentScheduleDao.retrieveLoanPayment(userEmiDetails.getLoan_id());

            if(userEmiDetails.getEmi_amount() == list.get(0).getEmi()){
                for(LoanPaymentSchedule val : list){
                    if(val.getDueAmount() !=0){
                        val.setDueAmount(0);
                        val.setPaymentUtrId(utr);// utr
                        val.setStatus("Paid");
                        val.setDateModified(new Date());
                        break;
                    }
                }

            }
            else{
                LocalDate presentDate = LocalDate.now();
                presentDate = presentDate.plusMonths(1);
                for(LoanPaymentSchedule val : list){
                    LocalDate due = val.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if(due.compareTo(presentDate) < 0){
                        val.setDueAmount(0);
                        val.setPaymentUtrId(utr);
                        val.setDateModified(new Date());
                        val.setStatus("Paid");
                    }
                }
            }

            loanPaymentScheduleDao.updateLoanPaymentSchedule(list);


            //if all emis paid

        }
        else {
            //make entry in loan payment with status = failed
            LoanPayment loanPayment = new LoanPayment(
                    userEmiDetails.getLoan_id(),
                    userEmiDetails.getEmi_amount(),
                    StringRandom.get(),
                    userEmiDetails.getPayment_mode(),
                    new Date(),
                    "Failed",
                    "Invalid Amount",
                    new Date(),
                    new Date());
            loanPaymentDao.saveLoanPayment(loanPayment);
//            throw new InvalidDueAmount("Amount Invalid");
        }
    }

    @Override
    public boolean check(String loanId){
        List<LoanPaymentSchedule> list = loanPaymentScheduleDao.retrieveLoanPayment(loanId);
        for(LoanPaymentSchedule val : list){
            if(val.getDueAmount() != 0){
                return false;
            }
        }
        return true;
    }
}

