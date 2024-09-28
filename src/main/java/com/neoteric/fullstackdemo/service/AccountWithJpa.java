package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountAddressEntity;
import com.neoteric.fullstackdemo.model.AccountEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountWithJpa {
//    public String accountJpa(Account account){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("accountJPA");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        try {
//            transaction.begin();
//            AccountEntity accountEntity = new AccountEntity();
//            accountEntity.setAccountNumber(UUID.randomUUID().toString());
//            accountEntity.setName(account.getName());
//            accountEntity.setPan(account.getPan());
//            accountEntity.setMobile(account.getMobile());
//            accountEntity.setBalance(account.getBalance());
//
//            List<AccountAddressEntity> accountAddressEntityList = new ArrayList<>();
//            AccountAddressEntity accountAddressEntity = new AccountAddressEntity();
//            accountAddressEntity.setAdd1(account.getAddress().getAdd1());
//            accountAddressEntity.setAdd2(account.getAddress().getAdd2());
//            accountAddressEntity.setState(account.getAddress().getState());
//            accountAddressEntity.setPincode(account.getAddress().getPincode());
//            accountAddressEntity.setStatuscode(1);
//            accountAddressEntity.setAccountEntity(accountEntity);
//            accountAddressEntityList.add(accountAddressEntity);
//
//            accountEntity.setAccountAddressEntityList(accountAddressEntityList);
//
//            em.persist(accountEntity);
//            transaction.commit();
//
//            return accountEntity.getAccountNumber();
//        } catch (Exception e) {
//            System.out.println("Exception  Occurred " +e);
//            }
//            return null;
//        }
    }


