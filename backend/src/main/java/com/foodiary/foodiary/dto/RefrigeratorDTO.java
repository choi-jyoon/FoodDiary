package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefrigeratorDTO {
    private String userEmail;
    private List<IngredientDTO> ingredients;
}
