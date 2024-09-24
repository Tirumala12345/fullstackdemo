package com.neoteric.fullstackdemo.controller;

import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountEntity;
import com.neoteric.fullstackdemo.service.AccountServiceWithSpringJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountJpaController {

    @Autowired
    AccountServiceWithSpringJpa accountServiceWithSpringJpa;

    @GetMapping(value = "/api/searchAccount/Datajpapan",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountByAccountAndPan(@RequestHeader("accountinput")
                                             String accountNumber, @RequestHeader("paninput") String pan) {
        return accountServiceWithSpringJpa.searchAccountByAccountNumberAndPan(accountNumber, pan);
    }


    @GetMapping(value = "/api/searchAccount/balance",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> searchByBalance(@RequestHeader("balanceinput") double balance){
        return accountServiceWithSpringJpa.accountLessThanBalance(balance);
    }

    @GetMapping(value = "/api/searchAccount/balanceGreater",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> searchByBalanceGreater(@RequestHeader("balanceinput") double balance){
        return accountServiceWithSpringJpa.accountGreaterThanBalance(balance);
    }

    @GetMapping(value = "/api/searchAccount/balanceRange",
            consumes = "application/json",
            produces = "application/json")
    public List<AccountEntity> searchByBalanceRange(@RequestHeader("lowerRangeinput") double lowerRange, @RequestHeader("upperRangeinput") double upperRange){
        return accountServiceWithSpringJpa.accountBetweenBalance(lowerRange, upperRange);
    }


    @GetMapping(value = "/api/searchAccount/balanceNull")
    public List<AccountEntity> getAccountsWithBalanceNull(){
        return accountServiceWithSpringJpa.findAccountsWithNullBalance();
    }

    @GetMapping(value = "/api/searchAccount/balanceNotNull")
    public List<AccountEntity> getAccountsWithBalanceNotNull(){
        return accountServiceWithSpringJpa.findAccountsWithBalanceNotNull();
    }

}
