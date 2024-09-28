package com.neoteric.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address", schema = "bank")
@Data
public class AccountAddressEntity {

    public AccountAddressEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "add1")
    private String add1;

    @Column(name = "add2")
    private String add2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "statuscode")
    private Integer statuscode;

    @Column(name = "accountnumber",insertable = false,updatable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "accountnumber", referencedColumnName = "accountnumber")
    private AccountEntity accountEntity;
}




