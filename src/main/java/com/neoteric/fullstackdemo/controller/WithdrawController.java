package com.neoteric.fullstackdemo.controller;

import com.neoteric.fullstackdemo.exception.AccountNotFoundException;
import com.neoteric.fullstackdemo.exception.InsufficientBalanceException;
import com.neoteric.fullstackdemo.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;

    @PostMapping(value = "/withdraw",
            produces = "application/json",
            consumes = "application/json")
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) throws Exception {
        try {
            return withdrawService.withdraw(accountNumber, amount);
        } catch (AccountNotFoundException e) {
            throw new Exception("Account not found", e);
        } catch (InsufficientBalanceException e) {
            throw new Exception("Insufficient balance", e);
        } catch (Exception e) {
            throw new Exception("Withdraw Failed", e);
        }
    }
}