package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.RefrigeratorDTO;
import com.foodiary.foodiary.entity.Refrigerator;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.IngredientRepository;
import com.foodiary.foodiary.repository.RefrigeratorRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    public RefrigeratorDTO getRefrigerator(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Refrigerator not found"));

        return new RefrigeratorDTO();
    }
}
