package com.foodiary.foodiary.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequestDTO {
    private String mealType;
    private String name;
    private String image;
    private String ingredient_list;
    private String recipe;
    private Double kcal;
    private Long user_id;

    public DiaryRequestDTO(String mealType, String name, String ingredient_list, Long user_id){
        this.mealType=mealType;
        this.name=name;
        this.ingredient_list=ingredient_list;
        this.user_id=user_id;
    }

}
