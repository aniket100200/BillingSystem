package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.jwtSec.JwtHelper;
import com.example.BhandeBillingSystem.services.impl.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto request) {

        this.doAuthenticate(request.getPhone(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getPhone());
        String token = this.helper.generateToken(userDetails);

//        UserRequestDto response = UserResponseDto.builder()
//                .jwtToken(token)
//                .username(userDetails.getUsername()).build();
        UserResponseDto response = new UserResponseDto();
        response.setMessage(token);
        response.setName(userDetails.getUsername());
        response.setEmail(userDetails.getUsername());
        response.setUuid(request.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
