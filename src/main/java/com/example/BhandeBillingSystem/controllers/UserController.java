package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.exceptions.user.UserNotFoundException;
import com.example.BhandeBillingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    final UserService userService;
    private final AuthController authController;

    @Autowired
    public UserController(UserService userService, AuthController authController) {
        this.userService = userService;
        this.authController = authController;
    }

    @GetMapping("/getMessage")
    public ResponseEntity<UserResponseDto> getMessage() throws UserNotFoundException {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setMessage("Hello World");
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("uuid") String uuid) {
        UserResponseDto dto;
        try{
            dto = userService.findByUuid(uuid);
        }catch (UserNotFoundException t){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-by-email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam("email") String email) {
        UserResponseDto dto;
        try{
            dto = userService.findUserByEmail(email);
        }catch (UserNotFoundException t){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getbyphone/{phone}")
    public ResponseEntity<UserResponseDto> getUserByPhone(@PathVariable("phone") String phone) {
        UserResponseDto dto;
        try{
             dto = userService.findByPhoneNumber(phone);
        }catch (UserNotFoundException t){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deleteByUuid")
    public ResponseEntity<String> deleteUserByUuid(@RequestParam("uuid") String uuid) {
        try{
            String resp=userService.deleteUser(uuid);
            return ResponseEntity.ok(resp);
        }catch (UserNotFoundException t){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam("uuid") String uuid,@RequestBody UserRequestDto dto) {
        UserResponseDto responseDto = userService.updateUser(uuid,dto);

        responseDto.setMessage("Updated Successfully");
        return ResponseEntity.ok(responseDto.getMessage());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDto dto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto dto) {
        return authController.login(dto);

    }
}
