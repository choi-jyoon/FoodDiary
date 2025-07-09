package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.UserDTO;
import com.foodiary.foodiary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String user() {
        return "message";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        if (userService.isEmailDuplicated(userDTO.getEmail())) {
            return "이미 존재하는 이메일입니다.";
        }
        userService.register(userDTO.getEmail(), userDTO.getPassword());
        return "회원가입 성공";
    }
}
