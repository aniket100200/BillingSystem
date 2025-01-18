package com.example.BhandeBillingSystem.repository;

import com.example.BhandeBillingSystem.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
   Bill findBillByUuid(String uuid);
   Bill findByUserUuid(String userUuid);
   List<Bill> findAllByUserUuid(String userUuid);
}
