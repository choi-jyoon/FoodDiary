package com.foodiary.foodiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String ingredient_list;
    @Column
    private String image;
    @Column
    private String recipe;
    @Column
    private Double kcal;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Food(String name, String ingredient_list, String image, String recipe, Double kcal,  User user) {
        this.name = name;
        this.ingredient_list = ingredient_list;
        this.image = image;
        this.recipe = recipe;
        this.kcal = kcal;
        this.user = user;
    }
}
