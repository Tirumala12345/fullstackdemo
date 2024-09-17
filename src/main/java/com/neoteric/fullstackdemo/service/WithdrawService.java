package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.exception.AccountNotFoundException;
import com.neoteric.fullstackdemo.exception.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class WithdrawService {

    private Connection connection;

    public WithdrawService() {
        this.connection = DBConnection.getConnection();
    }
    public double getBalance(String accountNumber) throws Exception {
        String query = "SELECT balance FROM account WHERE accountnumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                throw new AccountNotFoundException("Account Not Found" );
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching balance", e);
        }
    }

    public void updateBalance(String accountNumber, double newBalance) throws Exception {
        String updateQuery = "UPDATE account SET balance = ? WHERE accountnumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new AccountNotFoundException("Account not found for update");
            }
        }
    }

    public String withdraw(String accountNumber, double amount) throws Exception {
        double balance = getBalance(accountNumber);
        System.out.println("Balance is " +balance);
        if (balance >= amount) {
            updateBalance(accountNumber, balance - amount);
            return "Withdrawal Successful";
        } else {
            throw new InsufficientBalanceException("Insufficient balance");
        }
    }
}
