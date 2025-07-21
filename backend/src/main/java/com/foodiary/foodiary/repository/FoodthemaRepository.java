package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Foodthema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodthemaRepository extends JpaRepository<Foodthema, Long> {
    public Optional<Foodthema> findByName(String name);
}
