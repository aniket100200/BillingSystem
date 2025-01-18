package com.example.BhandeBillingSystem.services;

import com.example.BhandeBillingSystem.dtos.request.UtensileRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UtensileResponseDto;

import java.util.List;

public interface UtensileService {
    public String createUtensile(UtensileRequestDto utensile);
    public List<UtensileResponseDto> getAllUtensiles();
    public UtensileResponseDto getUtensile(String id);
    public String updateUtensile(String id, UtensileRequestDto utensile);
    public String deleteUtensile(String id);
}
