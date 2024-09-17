package com.neoteric.fullstackdemo.exception;

public class AccountNotFoundException extends Exception{
    public String message;

    public AccountNotFoundException(String msg) {
        this.message = msg;
    }
}
