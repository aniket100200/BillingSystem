package com.example.BhandeBillingSystem.jwtSec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and()
                // Disable CSRF since we're likely using token-based authentication
                .csrf(csrf -> csrf.disable())

                // Configure request authorization
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll() // Allow login endpoint
                        .requestMatchers("/test").authenticated()  // Require authentication for /test
                        .requestMatchers("/home/test").permitAll()
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/user/getMessage").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()              // Require authentication for all other requests
                )

                // Exception handling configuration
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))

                // Stateless session management for token-based authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Add custom filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




}
