package com.neoteric.fullstackdemo.controller;

import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.service.AccountService;
import com.neoteric.fullstackdemo.service.AccountServiceWithSpringJpa;
import com.neoteric.fullstackdemo.service.AccountWithJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountController {
    @PostMapping(value = "/createAccount",
    produces = "application/json",
    consumes = "application/json")

    public Account getAccountNumber(@RequestBody Account account) throws Exception {
        AccountService service=new AccountService();
        String accountNumber= service.createAccount(account);
        account.setAccountNumber(accountNumber);
        return account;
    }

    @PostMapping(value = "/createAccount/usingUI",
            produces = "application/json",
            consumes = "application/json")
    public Account createaccountoneToManyusingUI(@RequestBody Account account){
        AccountService accountService = new AccountService();
        String accountnumber =accountService.oneToManyUsingHibernateFromUI(account);
        account.setAccountNumber(accountnumber);
        return account;
    }


    @GetMapping(value = "/loansearch",
            produces = "application/json")
    public List<Account> getAccountNumber(@RequestParam ("accountnumber")
                                          String accountnumber)  {
        AccountService accountService=new AccountService();
        return accountService.searchAccount(accountnumber);
    }

    @PostMapping(value = "/accountJpa",
            produces = "application/json",
            consumes = "application/json")
    public Account createAccountJPA(@RequestBody Account account){
        AccountWithJpa accountWithJpa=new AccountWithJpa();
        String accountnumber= accountWithJpa.accountJpa(account);
        account.setAccountNumber(accountnumber);
        return account;
    }

    @Autowired
    AccountServiceWithSpringJpa accountServiceTest;

    @GetMapping(value = "/api/searchAccount/Datajpa",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumberByJpa(@RequestHeader ("accountinput")
                                    String accountNumber){
        return accountServiceTest.searchAccountByManagedJpa(accountNumber);
    }



}
