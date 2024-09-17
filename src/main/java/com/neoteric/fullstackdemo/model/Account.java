package com.neoteric.fullstackdemo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {

    private String name;
    private String accountNumber;
    private String pan;
    private  String mobile;
    private double balance;
    private Address address;

}
