package com.neoteric.fullstackdemo.controller;

import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.service.AccountServiceWithSpringJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
}
