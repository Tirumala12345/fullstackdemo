package com.neoteric.fullstackdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginRequest {
    private String cardNumber;
    private String pin;
}
