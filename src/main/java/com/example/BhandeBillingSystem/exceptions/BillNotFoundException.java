package com.example.BhandeBillingSystem.exceptions;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String message) {
        super(message);
    }
}
