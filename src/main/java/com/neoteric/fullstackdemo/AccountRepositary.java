package com.neoteric.fullstackdemo;

import com.neoteric.fullstackdemo.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositary extends JpaRepository<AccountEntity,String> {

    @Query("select a from AccountEntity a join fetch a.accountAddressEntityList ad where a.accountNumber=ad.accountNumber " +
            "and a.accountNumber=:accountNumber and ad.statuscode=:statuscode")
    AccountEntity getAccountEntityStatus(@Param("accountNumber") String accountNumber,@Param("statuscode") Integer statusCode);

    @Query("select a from AccountEntity a inner join a.accountAddressEntityList ad where  a.accountNumber=:accountNumber  and a.accountNumber=:accountNumber"  )
    AccountEntity getAccountEntity(@Param("accountNumber") String accountNumber);

    //    @Query("SELECT a FROM AccountEntity a WHERE a.accountNumber = :accountNumber AND a.pan = :pan")
//    AccountEntity getAccountEntity(@Param("accountNumber") String accountNumber, @Param("pan") String pan);
     AccountEntity findByAccountNumberAndPan(@Param("accountNumber") String accountNumber,
                                             @Param("pan") String pan);


    @Query("SELECT a FROM AccountEntity a WHERE a.balance < :balance")
    List<AccountEntity> getAccountByBalance(@Param("balance") double balance);
     List<AccountEntity> findByBalanceLessThan(@Param("balance") double balance);


     List<AccountEntity> findByBalanceGreaterThan(@Param("balance") double balance);


     List<AccountEntity>  findByBalanceBetween(double lowerRange,double upperRange);


     List<AccountEntity> findByBalanceIsNull();

     List<AccountEntity> findByBalanceIsNotNull();

     List<AccountEntity> findDistinctByBalance(@Param("balance") double balance);
}