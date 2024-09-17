package com.neoteric.fullstackdemo.controller;

import com.neoteric.fullstackdemo.model.LoginRequest;
import com.neoteric.fullstackdemo.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {

    @PostMapping("/atmLogin")
    public String login(@RequestBody LoginRequest loginRequest){
        LoginService loginService=new LoginService();
        return loginService.login(loginRequest);
    }
}
