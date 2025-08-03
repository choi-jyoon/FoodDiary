package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.FoodRequestDTO;
import com.foodiary.foodiary.dto.FoodResponseDTO;
import com.foodiary.foodiary.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private FoodService foodService;

    @GetMapping("")
    public List<FoodResponseDTO> getAllFood(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return foodService.getAllFood(email);

    }

    @PostMapping("/create")
    public FoodRequestDTO createFood(@RequestBody FoodRequestDTO foodDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return foodService.createFood(email, foodDTO);
    }

    @GetMapping("/{food_id}")
    public FoodResponseDTO getFood(@PathVariable("food_id") Long foodId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return foodService.getOneFood(email, foodId);
    }

    @PutMapping("/update/{food_id}")
    public FoodResponseDTO updateFood(@PathVariable("food_id") Long foodId, @RequestBody FoodRequestDTO foodDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return foodService.updateFood(foodId, foodDTO);
    }

    @DeleteMapping("/delete/{food_id")
    public FoodResponseDTO deleteFood(@PathVariable("food_id") Long foodId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return foodService.deleteFood(email, foodId);
    }
}
