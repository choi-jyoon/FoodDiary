package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Food;
import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public List<Food> findByUser(User user);
    public Optional<Food> findById(Long id);
}
