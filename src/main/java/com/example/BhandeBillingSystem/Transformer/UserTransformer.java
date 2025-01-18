package com.example.BhandeBillingSystem.Transformer;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.models.User;

public class UserTransformer {

    public static User userFromUserDto(UserRequestDto dto){
        User user = new User();


        user.setAddress(dto.getAddress());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setPassword(dto.getPassword());
        user.setState(dto.getState());
        user.setZip(dto.getZip());

        return user;


    }

    public static UserResponseDto userToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUuid(user.getUuid());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setCity(user.getCity());
        userResponseDto.setCountry(user.getCountry());
        userResponseDto.setState(user.getState());
        userResponseDto.setZip(user.getZip());
        return userResponseDto;
    }
}
