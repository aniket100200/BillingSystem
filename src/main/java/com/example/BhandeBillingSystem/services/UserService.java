package com.example.BhandeBillingSystem.services;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;

import java.util.List;

public interface UserService {
    String createUser(UserRequestDto user);
    UserResponseDto findUserByEmail(String email);
   UserResponseDto findByUuid(String uuid);
   UserResponseDto findByPhoneNumber(String phoneNumber);
   UserResponseDto updateUser(String uuid, UserRequestDto user);
   String deleteUser(String uuid);
   List<UserResponseDto> getAllUsers();

}
