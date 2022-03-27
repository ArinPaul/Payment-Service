package com.whizdm.payment_service.customexceptions;

public class InvalidDueAmount extends Exception{
    InvalidDueAmount(String message){
        super(message);
    }
}
