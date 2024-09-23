package com.neoteric.fullstackdemo;

import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositary extends JpaRepository<AccountEntity,String> {

     AccountEntity findByAccountNumberAndPan(@Param("accountNumber") String accountNumber,
                                             @Param("pan") String pan);

     List<AccountEntity> findByBalanceLessThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceGreaterThan(@Param("balance") double balance);

     List<AccountEntity> findByBalanceBetween(double lowerRange,double upperRange);

}
