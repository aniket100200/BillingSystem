package com.example.BhandeBillingSystem.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "t_user")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String uuid;

    String name;
    String email;

    @Column(unique = true)
    String phone;
    String address;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String uuid, String name, String email, String phone, String address, String role, String city, String state, String zip, String country, Timestamp createdAt, String password, List<Utensile> utensiles, List<Bill> bills) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.createdAt = createdAt;
        this.password = password;
        this.utensiles = utensiles;
        this.bills = bills;
    }

    public User() {
    }

    public User(String uuid, String name, String email, String phone, String address, String city, String state, String zip, String country, Timestamp createdAt, String password, List<Utensile> utensiles) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.createdAt = createdAt;
        this.password = password;
        this.utensiles = utensiles;
    }

    public String getUuid() {
        return uuid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Utensile> getUtensiles() {
        return utensiles;
    }

    public void setUtensiles(List<Utensile> utensiles) {
        this.utensiles = utensiles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", createdAt=" + createdAt +
                ", password='" + password + '\'' +
                '}';
    }

    String city;
    String state;
    String zip;
    String country;

    @CreationTimestamp
    Timestamp createdAt;

    String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Utensile> utensiles = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Bill> bills = new ArrayList<>();

    public User(String uuid, String name, String email, String phone, String address, String city, String state, String zip, String country, Timestamp createdAt, String password, List<Utensile> utensiles, List<Bill> bills) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.createdAt = createdAt;
        this.password = password;
        this.utensiles = utensiles;
        this.bills = bills;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
