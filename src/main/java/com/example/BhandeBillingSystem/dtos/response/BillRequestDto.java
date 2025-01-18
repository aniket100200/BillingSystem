package com.example.BhandeBillingSystem.dtos.response;

import com.example.BhandeBillingSystem.enums.Status;

public class BillRequestDto {
    Status status;
    String userid;
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BillRequestDto(Status status, String userid) {
        this.status = status;
        this.userid = userid;
    }

    public BillRequestDto() {
    }
}
