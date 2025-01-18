package com.example.BhandeBillingSystem.services;

import com.example.BhandeBillingSystem.dtos.response.BillRequestDto;
import com.example.BhandeBillingSystem.models.Bill;

import java.util.Date;
import java.util.List;

public interface BillService {
    void saveBill(BillRequestDto bill);
    void deleteBill(BillRequestDto bill,String billUuid);
    void updateBill(BillRequestDto bill, String billUuid);
    List<Bill> getAllBills();
    Bill getBillById(int id);
    List<Bill> getBillsByCustomerId(int customerId);
    List<Bill> getAllBillsByCustomerId(int customerId);
    List<Bill> getBillsByCustomerIdAndDate(int customerId, Date date);
    List<Bill> getBillsByDate(int customerId, Date date);
}
