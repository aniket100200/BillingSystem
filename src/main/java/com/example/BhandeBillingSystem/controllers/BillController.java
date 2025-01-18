package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
}
