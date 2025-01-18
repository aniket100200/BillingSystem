package com.example.BhandeBillingSystem.repository;

import com.example.BhandeBillingSystem.models.Utensile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtensileRepository extends JpaRepository<Utensile, String> {
}
