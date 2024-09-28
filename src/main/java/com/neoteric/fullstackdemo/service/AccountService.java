package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountAddressEntity;
import com.neoteric.fullstackdemo.model.AccountEntity;
import com.neoteric.fullstackdemo.model.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  AccountService {

    public String createAccount(Account account) throws Exception {

        String accountNumber = null;
        try {
            Connection connection = DBConnection.getConnection();
            Statement  stmt = connection.createStatement();
            accountNumber = UUID.randomUUID().toString();

            // insert into bank.account value('123','priya',
            // 'abc123','123456789',2000);

            String query = "insert into bank.account values(" +
                    "'" + accountNumber + "'" + ","
                    + "'" + account.getName() + "'" + ","
                    + "'" + account.getPan() + "'" + ","
                    + "'" + account.getMobile() + "'" + ","
                    + account.getBalance() + ")";

            int  status = stmt.executeUpdate(query);
            if (status==1) {
                System.out.println("Account is Created " + accountNumber);
            } else {
                throw new AccountCreationFailedException("Account Creation Failed Exception " +account.getPan());
            }
        } catch (SQLException e) {
            System.out.println("Exception in SQl Exception " +e);
        }catch(AccountCreationFailedException e){
            System.out.println("Exception Occured " +e);
            throw e;
        }
        return accountNumber;
    }


//    public String oneToManyUsingHibernateFromUI(Account account){
//        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
//        Session session= sessionFactory.openSession();
//        Transaction transaction= session.beginTransaction();
//
//        AccountEntity accountEntity=new AccountEntity();
//        accountEntity.setAccountNumber(UUID.randomUUID().toString());
//        accountEntity.setName(account.getName());
//        accountEntity.setPan(account.getPan());
//        accountEntity.setMobile(account.getMobile());
//        accountEntity.setBalance(account.getBalance());
//        List<AccountAddressEntity> accountAddressEntityList=new ArrayList<>();
//        AccountAddressEntity accountAddressEntity=new AccountAddressEntity();
//        accountAddressEntity.setAdd1(account.getAddress().getAdd1());
//        accountAddressEntity.setAdd2(account.getAddress().getAdd2());
//        accountAddressEntity.setState(account.getAddress().getState());
//        accountAddressEntity.setPincode(account.getAddress().getPincode());
//        accountAddressEntity.setStatuscode(1);
//        accountAddressEntity.setAccountEntity(accountEntity);
//        accountAddressEntityList.add(accountAddressEntity);
//        accountEntity.setAccountAddressEntityList(accountAddressEntityList);
//        session.persist(accountEntity);
//        transaction.commit();
//        return accountEntity.getAccountNumber();
//    }


    public List<Account> searchAccount(String accountNumber) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> criteriaQuery = criteriaBuilder.createQuery(AccountEntity.class);
        Root<AccountEntity> root = criteriaQuery.from(AccountEntity.class);

        if (accountNumber != null && !accountNumber.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.equal(root.get("accountNumber"), accountNumber));
        }

        List<AccountEntity> accountEntities = session.createQuery(criteriaQuery).getResultList();

        List<Account> accounts = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            Account account = Account.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .name(accountEntity.getName())
                    .mobile(accountEntity.getMobile())
                    .balance(accountEntity.getBalance())
                    .pan(accountEntity.getPan())
                    .build();
            accounts.add(account);
        }

        return accounts;
    }
}
//select * from accountEntity
//select * from accountEntity where accountnumber=inputAccount