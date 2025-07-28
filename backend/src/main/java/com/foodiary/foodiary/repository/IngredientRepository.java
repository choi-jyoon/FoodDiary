package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.Refrigerator;
import com.foodiary.foodiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    public List<Ingredient> findByRefrigerator(Refrigerator refrigerator);
    public Optional<Ingredient> findById(Long id);
}
