package com.foodiary.foodiary.controller;

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
    public String register(@RequestParam String email, @RequestParam String password) {
        if (userService.isEmailDuplicated(email)) {
            return "이미 존재하는 이메일입니다.";
        }
        userService.register(email, password);
        return "회원가입 성공";
    }
}
