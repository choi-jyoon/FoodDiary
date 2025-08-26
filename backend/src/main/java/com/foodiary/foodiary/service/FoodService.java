package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.FoodRequestDTO;
import com.foodiary.foodiary.dto.FoodResponseDTO;
import com.foodiary.foodiary.entity.Food;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.FoodRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public List<FoodResponseDTO> getAllFood(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Food> foods = foodRepository.findByUser(user);


        return foods.stream()
                .map(FoodResponseDTO::new)
                .collect(Collectors.toList());
    }

    public FoodResponseDTO createFood(String email, FoodRequestDTO foodDTO) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Food food = new Food(foodDTO.getName(), foodDTO.getIngredient_list(), foodDTO.getImage(), foodDTO.getRecipe(), foodDTO.getKcal(),user);
        System.out.println("----------------------foo");
        System.out.println(food);
        foodRepository.save(food);
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO getOneFood(String email, Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new UsernameNotFoundException("Food not found"));
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO updateFood(Long foodId, FoodRequestDTO foodDTO) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new UsernameNotFoundException("Food not found"));
        food.setName(foodDTO.getName());
        food.setIngredient_list(foodDTO.getIngredient_list());
        food.setImage(foodDTO.getImage());
        food.setKcal(foodDTO.getKcal());
        food.setRecipe(foodDTO.getRecipe());
        foodRepository.save(food);
        return new FoodResponseDTO(food);
    }

    public FoodResponseDTO deleteFood(String email, Long foodId) {
        foodRepository.deleteById(foodId);
        return new FoodResponseDTO(foodRepository.findById(foodId).get());
    }
}
