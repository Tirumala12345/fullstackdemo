package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.AccountRepositary;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountAddressEntity;
import com.neoteric.fullstackdemo.model.AccountEntity;
import com.neoteric.fullstackdemo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceWithSpringJpa {

    @Autowired
    AccountRepositary accountRepositary;
    public Account searchAccountByManagedJpa(String accountNumber){
        Account account=null;
        Optional<AccountEntity> optionalAccountEntity =accountRepositary.findById(accountNumber);
        if(optionalAccountEntity.isPresent()){
            AccountEntity accountEntity=optionalAccountEntity.get();

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .mobile(accountEntity.getMobile())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAdd1());
                address.setAdd2(accountAddressEntity.getAdd2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);
            }

        }
        return account;

    }

    public Account searchAccountByAccountNumberAndPan(String accountNumber,String pan){
        Account account=null;
        AccountEntity accountEntity =accountRepositary.findByAccountNumberAndPan(accountNumber,pan);
        if(accountEntity!= null){

            account=Account.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .mobile(accountEntity.getMobile())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .name(accountEntity.getName()).build();
            List<AccountAddressEntity> accountAddressEntityList =
                    accountEntity.getAccountAddressEntityList();
            if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
                AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
                Address address = new Address();
                address.setAdd1(accountAddressEntity.getAdd1());
                address.setAdd2(accountAddressEntity.getAdd2());
                address.setCity(accountAddressEntity.getCity());
                address.setPincode(accountAddressEntity.getPincode());
                address.setState(accountAddressEntity.getState());

                account.setAddress(address);
            }
        }
        return account;
    }

    }

