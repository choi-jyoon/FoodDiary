package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Foodthema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String nickname;
    private double weight;
    private double height;
    private double goalweight;
    private Long foodthemaId;
}
