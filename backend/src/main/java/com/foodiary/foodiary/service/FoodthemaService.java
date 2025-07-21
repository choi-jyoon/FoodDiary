package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.FoodthemaDTO;
import com.foodiary.foodiary.entity.Foodthema;
import com.foodiary.foodiary.repository.FoodthemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodthemaService {
    private final FoodthemaRepository foodthemaRepository;

    public FoodthemaDTO createFoodthema(String name, String comment) {
        if (foodthemaRepository.findByName(name).isPresent()) {throw new RuntimeException("Foodthema already exists");}
        Foodthema foodthema = new Foodthema();
        foodthema.setName(name);
        foodthema.setComment(comment);
        foodthemaRepository.save(foodthema);
        return new FoodthemaDTO(foodthema.getName(), foodthema.getComment());
    }
}
