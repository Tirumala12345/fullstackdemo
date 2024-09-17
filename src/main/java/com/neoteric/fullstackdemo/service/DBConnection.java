package com.neoteric.fullstackdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connection;
    public static Connection getConnection(){

        try {
            if(connection==null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "pass123");
            }else{
                System.out.println("Returning Existing Connection");
            }
        } catch (Exception e) {
            //Swallowing the exception
            System.out.println("Exception Occured In GetConnection " +e);
        }
        return connection;
    }
}
