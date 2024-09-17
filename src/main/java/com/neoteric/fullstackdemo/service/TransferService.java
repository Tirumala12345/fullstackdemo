package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.model.Transfer;

import java.sql.Connection;

public class TransferService {
    private Connection connection;

    public TransferService() {
        this.connection = DBConnection.getConnection();
    }

    public void transferAmount(Transfer transfer) {

    }
}