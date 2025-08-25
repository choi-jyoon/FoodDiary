package com.foodiary.foodiary.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDTO {
    private Date date;
    private String meal_type;
    private Long food_id;
    private Long user_id;
}
