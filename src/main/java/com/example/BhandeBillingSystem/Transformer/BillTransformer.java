package com.example.BhandeBillingSystem.Transformer;

import com.example.BhandeBillingSystem.dtos.response.BillResponseDto;
import com.example.BhandeBillingSystem.models.Bill;

public class BillTransformer {
    public static BillResponseDto billToBillResponseDto(Bill bill) {
        BillResponseDto billResponseDto = new BillResponseDto();
        billResponseDto.setUuid(bill.getUuid());
        billResponseDto.setStatus(bill.getStatus());
        billResponseDto.setAmount(100.23);
        return billResponseDto;
    }
}
