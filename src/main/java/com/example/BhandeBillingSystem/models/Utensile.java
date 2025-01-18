package com.example.BhandeBillingSystem.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "t_Utensile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utensile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String uuid;
    String name;
    String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    int quantity;
    double unitPrice; //we can also say rate

    @CreationTimestamp
    Timestamp createdTime;

    public String getUuid() {
        return uuid;
    }

    public Utensile() {
    }

    public Utensile(String uuid, String name, String imgUrl, int quantity, double unitPrice, Timestamp createdTime, Timestamp updatedTime, User user) {
        this.uuid = uuid;
        this.name = name;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.user = user;
    }

    public Utensile(String uuid, String name, int quantity, double unitPrice, Timestamp createdTime, Timestamp updatedTime, User user) {
        this.uuid = uuid;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.user = user;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @UpdateTimestamp
    Timestamp updatedTime;

    @ManyToOne
    @JoinColumn
    User user;

    @Override
    public String toString() {
        return "Utensile{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", user=" + user +
                '}';
    }
}
