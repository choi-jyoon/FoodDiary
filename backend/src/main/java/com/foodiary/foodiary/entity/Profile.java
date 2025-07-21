package com.foodiary.foodiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = true)
    private double weight;
    @Column(nullable = true)
    private double height;
    @Column(nullable = true)
    private double goal_weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_thema_id", nullable = true)
    private Foodthema foodthema;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public void updateProfile(String nickname, double weight, double height, double goal_weight, Foodthema foodthema) {
        this.nickname = nickname;
        this.weight = weight;
        this.height = height;
        this.goal_weight = goal_weight;
        this.foodthema = foodthema;
    }
}
