package com.example.BhandeBillingSystem.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UtensileResponseDto {
    @JsonProperty("name")
    String name;
    String uuid;
    Double price;
    String imageURL;
    @JsonProperty("quantity")
    int quantity;

    public UtensileResponseDto() {
    }

    public UtensileResponseDto(String name, String uuid, Double price, String imageURL, int quantity) {
        this.name = name;
        this.uuid = uuid;
        this.price = price;
        this.imageURL = imageURL;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
