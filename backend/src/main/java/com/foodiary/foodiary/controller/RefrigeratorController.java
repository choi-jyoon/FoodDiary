package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.Refrigerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {

    @GetMapping("/")
    public String getMyRefrigerator() {
        return "refrigerator";
    }

    @PostMapping("/create")
    public String createRefrigerator(@RequestBody Ingredient ingredient) {
        return "success to add ingredient";
    }

    @PutMapping("/update/{ingredient_id}")
    public String updateRefrigerator(@PathVariable Long ingredient_id, @RequestBody Ingredient ingredient) {
        return "success to update ingredient";
    }
}
