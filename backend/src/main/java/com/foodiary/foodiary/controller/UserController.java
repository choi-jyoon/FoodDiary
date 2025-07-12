package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.LoginRequest;
import com.foodiary.foodiary.dto.UserDTO;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().body(Map.of("token", token));
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        if (userService.isEmailDuplicated(userDTO.getEmail())) {
            return "이미 존재하는 이메일입니다.";
        }
        userService.register(userDTO.getEmail(), userDTO.getPassword());
        return "회원가입 성공";
    }

//    @PostMapping("/logout")
//    public String logout(@RequestBody token) {
//
//    }
}
