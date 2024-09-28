package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.AccountRepositary;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountAddressEntity;
import com.neoteric.fullstackdemo.model.AccountEntity;
import com.neoteric.fullstackdemo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountServiceWithSpringJpa {

    @Autowired
    AccountRepositary accountRepositary;

    public List<AccountEntity> accountLessThanBalance(double balance) {
        return accountRepositary.findByBalanceLessThan(balance);
    }

    public List<AccountEntity> accountGreaterThanBalance(double balance) {
        return accountRepositary.findByBalanceGreaterThan(balance);
    }

    public List<AccountEntity> accountBetweenBalance(double lowerRange, double upperRange) {
        return accountRepositary.findByBalanceBetween(lowerRange, upperRange);
    }

    public List<AccountEntity> findAccountsWithNullBalance() {
        return accountRepositary.findByBalanceIsNull();
    }

    public List<AccountEntity> findAccountsWithBalanceNotNull() {
        return accountRepositary.findByBalanceIsNotNull();
    }

    public List<AccountEntity> findDistinctAccountsByBalance(double balance) {
        return accountRepositary.findDistinctByBalance(balance);
    }


//    public Account searchAccountByManagedJpa(String accountNumber) {
//        Account account = null;
//        Optional<AccountEntity> optionalAccountEntity = accountRepositary.findById(accountNumber);
//        if (optionalAccountEntity.isPresent()) {
//            AccountEntity accountEntity = optionalAccountEntity.get();
//
//            account = Account.builder()
//                    .accountNumber(accountEntity.getAccountNumber())
//                    .mobile(accountEntity.getMobile())
//                    .balance(accountEntity.getBalance())
//                    .pan(accountEntity.getPan())
//                    .name(accountEntity.getName()).build();
//            List<AccountAddressEntity> accountAddressEntityList =
//                    accountEntity.getAccountAddressEntityList();
//            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
//                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
//                Address address = new Address();
//                address.setAdd1(accountAddressEntity.getAdd1());
//                address.setAdd2(accountAddressEntity.getAdd2());
//                address.setCity(accountAddressEntity.getCity());
//                address.setPincode(accountAddressEntity.getPincode());
//                address.setState(accountAddressEntity.getState());
//
//                account.setAddress(address);
//            }
//
//        }
//        return account;
//
//    }

//    public Account searchAccountByAccountNumberAndPan(String accountNumber, String pan) {
//        Account account = null;
//        AccountEntity accountEntity = accountRepositary.findByAccountNumberAndPan(accountNumber, pan);
//        if (accountEntity != null) {
//
//            account = Account.builder()
//                    .accountNumber(accountEntity.getAccountNumber())
//                    .mobile(accountEntity.getMobile())
//                    .balance(accountEntity.getBalance())
//                    .pan(accountEntity.getPan())
//                    .name(accountEntity.getName()).build();
//            List<AccountAddressEntity> accountAddressEntityList =
//                    accountEntity.getAccountAddressEntityList();
//            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
//                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
//                Address address = new Address();
//                address.setAdd1(accountAddressEntity.getAdd1());
//                address.setAdd2(accountAddressEntity.getAdd2());
//                address.setCity(accountAddressEntity.getCity());
//                address.setPincode(accountAddressEntity.getPincode());
//                address.setState(accountAddressEntity.getState());
//
//                account.setAddress(address);
//            }
//        }
//        return account;
//    }

//    public Account searchAccountDataJpa(String accountNumber){
//        Account account = null;
//        AccountEntity accountEntity = accountRepositary.getAccountEntity(accountNumber);
//        if (accountEntity != null) {
//
//            account = Account.builder()
//                    .accountNumber(accountEntity.getAccountNumber())
//                    .mobile(accountEntity.getMobile())
//                    .balance(accountEntity.getBalance())
//                    .pan(accountEntity.getPan())
//                    .name(accountEntity.getName()).build();
//            List<AccountAddressEntity> accountAddressEntityList =
//                    accountEntity.getAccountAddressEntityList();
//            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
//                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
//                Address address = new Address();
//                address.setAdd1(accountAddressEntity.getAdd1());
//                address.setAdd2(accountAddressEntity.getAdd2());
//                address.setCity(accountAddressEntity.getCity());
//                address.setPincode(accountAddressEntity.getPincode());
//                address.setState(accountAddressEntity.getState());
//
//                account.setAddress(address);
//            }
//        }
//        return account;
//    }

    public List<Account> searchAccountByAddressStatusJPQl(String accountnumber,String pan){
        List<Account> accountList=new ArrayList<>();
        AccountEntity accountEntity =accountRepositary.getAccountEntityStatus(accountnumber,1);
        if(accountEntity!= null){

          Account  account=Account.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .mobile(accountEntity.getMobile())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
             List<Address> addressList=   accountAddressEntityList.stream().map(accountAddressEntity ->{
                    Address address = new Address();
                    address.setAdd1(accountAddressEntity.getAdd1());
                    address.setAdd2(accountAddressEntity.getAdd2());
                    address.setCity(accountAddressEntity.getCity());
                    address.setPincode(accountAddressEntity.getPincode());
                    address.setState(accountAddressEntity.getState());
                    return address;
                }).collect(Collectors.toList());
                account.setAddress(addressList);
            }
        }
        return accountList;
    }
}