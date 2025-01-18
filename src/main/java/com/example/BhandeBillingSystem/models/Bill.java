package com.example.BhandeBillingSystem.models;

import com.example.BhandeBillingSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "t_bill")
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String uuid;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    public Bill(Timestamp createdAt, String uuid, Timestamp updatedAt, Status status) {
        this.createdAt = createdAt;
        this.uuid = uuid;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Bill() {
    }

    @Enumerated(EnumType.STRING)
    Status status;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn
    User user;

    public Bill(String uuid, Timestamp createdAt, Timestamp updatedAt, Status status, User user) {
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
