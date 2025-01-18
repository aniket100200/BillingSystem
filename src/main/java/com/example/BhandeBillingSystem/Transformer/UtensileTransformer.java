package com.example.BhandeBillingSystem.Transformer;

import com.example.BhandeBillingSystem.dtos.request.UtensileRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UtensileResponseDto;
import com.example.BhandeBillingSystem.models.Utensile;

public class UtensileTransformer {

    public static UtensileResponseDto transformUtensile(Utensile utensile) {
        UtensileResponseDto utensileResponseDto = new UtensileResponseDto();
        utensileResponseDto.setName(utensile.getName());
        utensileResponseDto.setQuantity(utensile.getQuantity());
        utensileResponseDto.setPrice(utensile.getUnitPrice());
        utensileResponseDto.setImageURL(utensile.getImgUrl());
        utensileResponseDto.setUuid(utensile.getUuid());
        return utensileResponseDto;
    }

    public static Utensile transformUtensile(UtensileRequestDto utensileResponseDto) {
        Utensile utensile = new Utensile();
        utensile.setName(utensileResponseDto.getName());
        utensile.setQuantity(utensileResponseDto.getQuantity());
        utensile.setUnitPrice(utensileResponseDto.getPrice());
        utensile.setImgUrl(utensileResponseDto.getImageUrl());
        return utensile;
    }
}
