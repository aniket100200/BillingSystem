package com.example.BhandeBillingSystem.controllers;

import com.example.BhandeBillingSystem.dtos.request.UserRequestDto;
import com.example.BhandeBillingSystem.dtos.response.UserResponseDto;
import com.example.BhandeBillingSystem.exceptions.user.UserNotFoundException;
import com.example.BhandeBillingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    final UserService userService;
    private final AuthController authController;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/src/main/resources/static/bhande-billing-system/public/profiles";

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

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam("uuid") String uuid,@RequestBody UserRequestDto dto) {
        UserResponseDto responseDto = userService.updateUser(uuid,dto);

        responseDto.setMessage("Updated Successfully");
        return ResponseEntity.ok(responseDto.getMessage());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto dto) {
        return authController.login(dto);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        try{
            List<UserResponseDto> list = userService.getAllUsers();
            return ResponseEntity.ok(list);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }



    @PostMapping("/image/{name}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,@PathVariable("name") String profileName) {
       try{
           //create Folder if not exists
           Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
           // Generate a unique file name
           String fileName = profileName +".jpg";
           String filePath = UPLOAD_DIRECTORY+"/" + fileName;

           // Save file
           file.transferTo(new File(filePath));

           return ResponseEntity.ok("{\"message\": \"File uploaded successfully\", \"filePath\": \"" + fileName + "\"}");

       }catch (Exception e){
           return ResponseEntity.notFound().build();
       }
    }
}
