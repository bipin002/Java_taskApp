package com.taskfusion.taskApp.controller;

import com.taskfusion.taskApp.Utility.JwtToken;
import com.taskfusion.taskApp.services.LoginUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Login {
    @Autowired
    private LoginUserServices _loginservices;

    @GetMapping("/userlogin")
    public ResponseEntity<String> userlogin(@RequestParam String username, @RequestParam String password) {
        Boolean isUserValid = _loginservices.loginuser(username, password);
        if (isUserValid) {
            String userToken = JwtToken.generateToken(username);
            return ResponseEntity.ok("token:" + userToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username and password");
        }

    }
}
