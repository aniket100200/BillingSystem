package com.example.BhandeBillingSystem.repository;

import com.example.BhandeBillingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByUuid(String uuid);
    User findByPhone(String phone);
}
