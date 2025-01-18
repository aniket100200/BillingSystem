package com.example.BhandeBillingSystem.dtos.response;

import com.example.BhandeBillingSystem.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class BillResponseDto {
    String uuid;
    Status status;
    Double amount;

    public BillResponseDto(String uuid, Double amount, Status status) {
        this.uuid = uuid;
        this.amount = amount;
        this.status = status;
    }

    public BillResponseDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
