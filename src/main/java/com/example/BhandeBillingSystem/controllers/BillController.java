package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.BillRequestDto;
import com.example.BhandeBillingSystem.dtos.response.BillResponseDto;
import com.example.BhandeBillingSystem.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/save")
    public ResponseEntity saveBill(@RequestBody BillRequestDto bill) {

       try {
           billService.saveBill(bill);
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok("Bill Saved Successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteBill(@RequestBody BillRequestDto bill, @RequestParam("billId") String billId ) {
        billService.deleteBill(bill, billId);
        return ResponseEntity.ok("Bill Deleted Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity updateBill(@RequestBody BillRequestDto bill,@RequestParam  String uuid) {
        billService.updateBill(bill, uuid);
        return ResponseEntity.ok("Bill Updated Successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllBills() {

      List<BillResponseDto> bills = billService.getAllBills();
      return ResponseEntity.ok(bills);
    }

    @GetMapping("/getById")
    public ResponseEntity getBillById(@RequestParam String billId) {
        BillResponseDto bill = billService.getBillById(billId);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/getAllBy/{customerId}")
    public ResponseEntity getAllBillsByCustomerId(@PathVariable String customerId) {
        List<BillResponseDto> bills = billService.getAllBillsByCustomerId(customerId);
        return ResponseEntity.ok(bills);
    }





}
