package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.UtensileRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UtensileResponseDto;
import com.example.BhandeBillingSystem.services.UtensileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utensile")
public class UtensileController {
    final UtensileService utensileService;
    @Autowired
    public UtensileController(UtensileService utensileService) {
        this.utensileService = utensileService;
    }

    @PostMapping("/create")
    public ResponseEntity createUtensile(@RequestBody UtensileRequestDto utensile) {
        return ResponseEntity.ok(utensileService.createUtensile(utensile));
    }

    @GetMapping("/get")
    public ResponseEntity getUtensile() {
        return ResponseEntity.ok(utensileService.getAllUtensiles());
    }

    @GetMapping("/byId")
    public ResponseEntity findByUtensileId(@RequestParam("utensileId") String utensileId) {
        try{
            UtensileResponseDto utensile =utensileService.getUtensile(utensileId);
            return utensile != null ? ResponseEntity.ok(utensile) : ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUtensile(@RequestParam("uuid") String utensileId) {
        try{
          String message =utensileService.deleteUtensile(utensileId);
          return ResponseEntity.ok(message);
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUtensile(@RequestParam("uuid") String uuid,@RequestBody UtensileRequestDto utensile) {
        return ResponseEntity.ok(utensileService.updateUtensile(uuid,utensile));
    }



}
