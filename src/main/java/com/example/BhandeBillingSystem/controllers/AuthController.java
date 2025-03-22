package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.jwtSec.JwtHelper;
import com.example.BhandeBillingSystem.models.User;
import com.example.BhandeBillingSystem.repository.UserRepository;
import com.example.BhandeBillingSystem.services.UserService;
import com.example.BhandeBillingSystem.services.impl.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {


    final private CustomUserDetailsService userDetailsService;
    final private UserRepository userRepository;

    final private AuthenticationManager manager;


   final private JwtHelper helper;

    final private Logger logger;

    @Autowired
    public AuthController(CustomUserDetailsService userDetailsService, UserRepository userRepository, AuthenticationManager manager, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
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

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
            User user = userRepository.findByEmail(request.getEmail());
            if(user == null && request.getPhone()!=null) {
                throw new BadCredentialsException("Invalid phone or password");
            }
            UserResponseDto response = new UserResponseDto();
            response.setMessage(token);
            response.setName(user.getName());
            response.setEmail(userDetails.getUsername());
            response.setPhone(user.getPhone());
            response.setUuid(user.getUuid());
            response.setCity(user.getCity());
            response.setCountry(user.getCountry());
            response.setState(user.getState());
            response.setZip(user.getZip());
            response.setAddress(user.getAddress());
            response.setRole(user.getRole());
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
