package com.foodiary.foodiary.dto;

import com.foodiary.foodiary.entity.Diary;
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

    public DiaryDTO(Diary newDiary) {
        this.date = newDiary.getDate();
        this.meal_type = newDiary.getMeal_type();
        this.food_id = newDiary.getFood().getId();
        this.user_id = newDiary.getUser().getId();
    }
}
