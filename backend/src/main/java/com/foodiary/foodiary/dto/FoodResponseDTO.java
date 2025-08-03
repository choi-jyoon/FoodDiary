package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Food;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponseDTO {
    private Long id;
    private String name;
    private String image;
    private String ingredient_list;
    private String recipe;
    private Double kcal;
    private Long user_id;

    public FoodResponseDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.image = food.getImage();
        this.ingredient_list = food.getIngredient_list();
        this.recipe = food.getRecipe();
        this.kcal = food.getKcal();
        this.user_id = food.getUser().getId();
    }
}
