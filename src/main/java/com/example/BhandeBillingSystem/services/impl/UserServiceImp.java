package com.example.BhandeBillingSystem.services.impl;

import com.example.BhandeBillingSystem.Transformer.UserTransformer;
import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.exceptions.user.UserNotFoundException;
import com.example.BhandeBillingSystem.models.User;
import com.example.BhandeBillingSystem.repository.UserRepository;
import com.example.BhandeBillingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {


    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder1;
    }

    @Override
    public String createUser(UserRequestDto dto) {
        User user = UserTransformer.userFromUserDto(dto);
        User u = userRepository.findByPhone(dto.getPhone());
        if (u != null) {
            return "User Already Exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(dto.getRole());
            userRepository.save(user);
            return "User created: " + user.toString();


    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        UserResponseDto dto = UserTransformer.userToUserResponseDto(user);
        return dto;
    }

    @Override
    public UserResponseDto findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        UserResponseDto dto = UserTransformer.userToUserResponseDto(user);
        return dto;
    }

    @Override
    public UserResponseDto findByPhoneNumber(String phoneNumber) throws UserNotFoundException {
        User user = userRepository.findByPhone(phoneNumber);
        System.out.println(user.toString());
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        UserResponseDto dto = UserTransformer.userToUserResponseDto(user);
        return dto;
    }

    @Override
    public UserResponseDto updateUser(String uuid, UserRequestDto dto) {
        User user = UserTransformer.userFromUserDto(dto);
        User user1 = userRepository.findByUuid(uuid);
        if(user1 == null) {
            throw new UserNotFoundException("User not found");
        }


        user.setUuid(uuid);
        if(dto.getPassword()!=null)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        else  user.setPassword(user1.getPassword());

       user = userRepository.save(user);
        return UserTransformer.userToUserResponseDto(user);
    }

    @Override
    public String deleteUser(String uuid) {
        User user = userRepository.findByUuid(uuid);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.delete(user);
        return "User deleted: " + user.toString();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();
        for(User user : users) {
            dtos.add(UserTransformer.userToUserResponseDto(user));
        }
        return dtos;
    }
}
