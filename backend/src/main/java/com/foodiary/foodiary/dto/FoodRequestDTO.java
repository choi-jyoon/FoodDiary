package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Food;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequestDTO {
    private String name;
    private String image;
    private String ingredient_list;
    private String recipe;
    private Double kcal;
    private Long user_id;

    public FoodRequestDTO(Food food) {
        this.name = food.getName();
        this.image = food.getImage();
        this.ingredient_list = food.getIngredient_list();
        this.recipe = food.getRecipe();
        this.kcal = food.getKcal();
        this.user_id = food.getUser().getId();
    }

    public FoodRequestDTO(String name, String ingredient_list, Long user_id) {
        this.name=name;
        this.ingredient_list=ingredient_list;
        this.user_id=user_id;
    }
}
