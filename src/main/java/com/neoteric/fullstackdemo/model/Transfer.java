package com.neoteric.fullstackdemo.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Transfer {
    public String fromAccount;
    public String toAccount;
    public double amount;
}
