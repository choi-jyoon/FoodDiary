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
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "ingredient_list", nullable = true)
    private String ingredient_list;
    @Column(name = "image", nullable = true)
    private String image;
    @Column(name = "recipe", nullable = true)
    private String recipe;
    @Column(name = "kcal", nullable = true)
    private Double kcal;
    @ManyToOne
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
    public Food(String name, String ingredient_list, User user) {
        this.name=name;
        this.ingredient_list=ingredient_list;
        this.user = user;
    }
}
