package com.bookstore.controllers;

import com.bookstore.config.JwtUtil;
import com.bookstore.models.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
private final AuthenticationManager authenticationManager;
private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUti) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUti;
    }

    @PostMapping("/login")
    ResponseEntity<Void> login(@RequestBody UserCredentials credentials){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(),credentials.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        String jwt = this.jwtUtil.create(credentials.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }
}
