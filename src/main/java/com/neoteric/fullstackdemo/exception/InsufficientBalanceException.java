package com.neoteric.fullstackdemo.exception;

public class InsufficientBalanceException extends Exception{

    public String message;

    public InsufficientBalanceException(String msg) {
        this.message = msg;
    }
}
