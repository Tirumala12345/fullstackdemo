package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.model.LoginRequest;

import java.sql.*;

public class LoginService {

    private Connection connection;

    public LoginService() {
        this.connection = DBConnection.getConnection(); // Get the connection from DBConnection class
    }

    public String login(LoginRequest loginRequest){
        try {
            String query = "SELECT * FROM atm WHERE cardnumber = ? AND pin = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, loginRequest.getCardNumber());
            pstmt.setString(2, loginRequest.getPin());

            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return "Login Successful";
            }else{
                return "Invalid Credentials";
            }
        }catch (SQLException e) {
            System.out.println("Exception occurred " +e);
            return "Login Failed";
        }
    }
}
