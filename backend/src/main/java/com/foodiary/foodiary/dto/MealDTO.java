package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Food;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    private String mealType;
    private Food food;


}
