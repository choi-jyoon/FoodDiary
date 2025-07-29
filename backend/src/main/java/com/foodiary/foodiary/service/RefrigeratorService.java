package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.IngredientDTO;
import com.foodiary.foodiary.dto.RefrigeratorDTO;
import com.foodiary.foodiary.entity.Ingredient;
import com.foodiary.foodiary.entity.Refrigerator;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.IngredientRepository;
import com.foodiary.foodiary.repository.RefrigeratorRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    public RefrigeratorDTO getRefrigerator(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user).orElseGet(() ->
            {Refrigerator newRefrigerator =  new Refrigerator(0, new Date(), user );
            return  refrigeratorRepository.save(newRefrigerator);});

        List<Ingredient> ingredients = ingredientRepository.findByRefrigerator(refrigerator);
        List<IngredientDTO> ingredientDTOs = ingredients.stream()
                .map(i -> new IngredientDTO(
                        i.getId(),
                        i.getName(),
                        i.getAmount(),
                        i.getCategory(),
                        i.getExpirationDate(),
                        i.getSection()))
                .toList();
        System.out.println(refrigerator);
        return new RefrigeratorDTO(user.getEmail(), ingredientDTOs);
    }

    public void createIngredient(String email, IngredientDTO ingredientDTO) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user).orElseGet(() ->
        {Refrigerator newRefrigerator =  new Refrigerator(0, new Date(), user );
            System.out.println("refrigerator" + newRefrigerator);
            return  refrigeratorRepository.save(newRefrigerator);});
        System.out.println(refrigerator);
        Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getAmount(), ingredientDTO.getCategory(), ingredientDTO.getExpirationDate(), ingredientDTO.getSection(), refrigerator);
        ingredientRepository.save(ingredient);

    }

    public IngredientDTO updateIngredient(Long ingredientId, IngredientDTO ingredientDTO) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new UsernameNotFoundException("Ingredient not found"));
        IngredientDTO newIngredientDTO = new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getAmount(), ingredient.getCategory(), ingredient.getExpirationDate(), ingredient.getSection());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setCategory(ingredientDTO.getCategory());
        ingredient.setExpirationDate(ingredientDTO.getExpirationDate());
        ingredient.setSection(ingredientDTO.getSection());
        ingredientRepository.save(ingredient);
        return newIngredientDTO;
    }
}
