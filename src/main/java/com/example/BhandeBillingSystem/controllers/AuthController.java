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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {


    final private CustomUserDetailsService userDetailsService;

    final private AuthenticationManager manager;


   final private JwtHelper helper;

    final private Logger logger;

    @Autowired
    public AuthController(CustomUserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.helper = helper;
        this.logger = LoggerFactory.getLogger(AuthController.class);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto request) {

       try {
            this.doAuthenticate(request.getEmail(), request.getPassword());


            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = this.helper.generateToken(userDetails);


            UserResponseDto response = new UserResponseDto();
            response.setMessage(token);
            response.setName(userDetails.getUsername());
            response.setEmail(userDetails.getUsername());
            response.setUuid(request.getEmail());
           return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception t){
           return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
       }

    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
