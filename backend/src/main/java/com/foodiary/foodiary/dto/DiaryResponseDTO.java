package com.foodiary.foodiary.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponseDTO {
    private Date date;
    private List<MealDTO> meals;
}
