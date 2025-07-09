package com.foodiary.foodiary.service;

import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(String email, String password) {
        String encoded = passwordEncoder.encode(password);
        User user = new User(null, email, encoded);
        userRepository.save(user);
    }

    public boolean isEmailDuplicated(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
