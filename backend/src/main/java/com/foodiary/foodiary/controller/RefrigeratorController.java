package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.IngredientDTO;
import com.foodiary.foodiary.dto.RefrigeratorDTO;
import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.Refrigerator;
import com.foodiary.foodiary.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    @GetMapping("")
    public RefrigeratorDTO getMyRefrigerator() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return refrigeratorService.getRefrigerator(email);
    }

    @PostMapping("/create")
    public String createRefrigerator(@RequestBody IngredientDTO ingredientDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        refrigeratorService.createIngredient(email, ingredientDTO);
        return "success to add ingredient";
    }

    @PutMapping("/update/{ingredient_id}")
    public String updateRefrigerator(@PathVariable Long ingredient_id, @RequestBody Ingredient ingredient) {
        return "success to update ingredient";
    }
}
