package com.example.BhandeBillingSystem.services.impl;

import com.example.BhandeBillingSystem.Transformer.UtensileTransformer;
import com.example.BhandeBillingSystem.dtos.request.UtensileRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UtensileResponseDto;
import com.example.BhandeBillingSystem.exceptions.utensile.UtensileNotFoundException;
import com.example.BhandeBillingSystem.models.Utensile;
import com.example.BhandeBillingSystem.repository.UtensileRepository;
import com.example.BhandeBillingSystem.services.UtensileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtensileServiceImp implements UtensileService {

    final UtensileRepository utensileRepository;

    @Autowired
    public UtensileServiceImp(UtensileRepository utensileRepository) {
        this.utensileRepository = utensileRepository;
    }

    @Override
    public String createUtensile(UtensileRequestDto dto) {
        Utensile utensile = new Utensile();
        utensile.setName(dto.getName());
        utensile.setQuantity(dto.getQuantity());
        utensile.setUnitPrice(dto.getPrice());
        utensile.setImgUrl(dto.getImageUrl());
        utensileRepository.save(utensile);
        return "Utensile Added Successfully";
    }

    @Override
    public List<UtensileResponseDto> getAllUtensiles() {
        List<Utensile> utensiles = utensileRepository.findAll();
        List<UtensileResponseDto> utensileResponseDtos = new ArrayList<>();
        for(Utensile utensile : utensiles) {
            UtensileResponseDto utensileResponseDto = UtensileTransformer.transformUtensile(utensile);
            utensileResponseDtos.add(utensileResponseDto);
        }
        return utensileResponseDtos;
    }

    @Override
    public UtensileResponseDto getUtensile(String id)throws UtensileNotFoundException {

        Optional utensile = utensileRepository.findById(id);
        if(!utensile.isPresent()) {
            throw new UtensileNotFoundException("Utensile Not Found with id");
        }

        return UtensileTransformer.transformUtensile((Utensile) utensile.get());
    }

    @Override
    public String updateUtensile(String id, UtensileRequestDto requestDto)throws UtensileNotFoundException {
        Utensile utensile = utensileRepository.findById(id).get();
        if(utensile == null) {
            throw new UtensileNotFoundException("Utensile Not Found with id");
        }

        String uuid = utensile.getUuid();
       utensile = UtensileTransformer.transformUtensile(requestDto);
       utensile.setUuid(uuid);

        utensileRepository.save(utensile);
        return "Utesnile With Id = "+id+" has been updated Successfully";
    }

    @Override
    public String deleteUtensile(String id) {
        Utensile utensile = utensileRepository.findById(id).get();
        if(utensile == null) {
            throw new UtensileNotFoundException("Utensile Not Found with id");
        }

        utensileRepository.delete(utensile);
        return "Utensile  Deleted Successfully";
    }
}
