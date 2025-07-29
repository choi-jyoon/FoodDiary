package com.foodiary.foodiary.entity;

import com.foodiary.foodiary.dto.IngredientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ingredient")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "section")
    private String section;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    public Ingredient(String name, double amount, String category, Date expirationDate, String section, Refrigerator refrigerator) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.expirationDate = expirationDate;
        this.section = section;
        this.refrigerator = refrigerator;
    }

}
