package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Refrigerator;
import com.foodiary.foodiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    public Optional<Refrigerator> findByUser(User user);
}
