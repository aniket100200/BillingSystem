package com.example.BhandeBillingSystem.services;

import com.example.BhandeBillingSystem.dtos.request.BillRequestDto;
import com.example.BhandeBillingSystem.dtos.response.BillResponseDto;

import java.util.Date;
import java.util.List;

public interface BillService {
    void saveBill(BillRequestDto bill);
    void deleteBill(BillRequestDto bill,String billUuid);
    void updateBill(BillRequestDto bill, String billUuid);
    List<BillResponseDto> getAllBills();


    BillResponseDto getBillById(String id);

    List<BillResponseDto> getBillsByCustomerId(String customerId);
    List<BillResponseDto> getAllBillsByCustomerId(String customerId);
    List<BillResponseDto> getBillsByCustomerIdAndDate(String customerId, Date date);
    List<BillResponseDto> getBillsByDate(String customerId, Date date);
}
