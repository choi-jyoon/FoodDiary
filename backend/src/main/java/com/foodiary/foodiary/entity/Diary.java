package com.foodiary.foodiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String meal_type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Diary(Date date, String meal_type, Food food, User user) {
        this.date = date;
        this.meal_type = meal_type;
        this.food = food;
        this.user = user;
    }
}


