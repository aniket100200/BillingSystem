package com.example.BhandeBillingSystem.repository;

import com.example.BhandeBillingSystem.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
   Bill findBillByUuid(String uuid);
}
