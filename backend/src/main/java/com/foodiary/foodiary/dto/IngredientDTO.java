package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Ingredient;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private Long id;
    private String name;
    private double amount;
    private String category;
    private Date expirationDate;
    private String section;

}
