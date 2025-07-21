package com.foodiary.foodiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food_thema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Foodthema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column(nullable = true)
    private String comment;
}
