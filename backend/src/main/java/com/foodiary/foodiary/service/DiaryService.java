package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.*;
import com.foodiary.foodiary.entity.Diary;
import com.foodiary.foodiary.entity.Food;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.DiaryRepository;
import com.foodiary.foodiary.repository.FoodRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final FoodService foodService;

    public DiaryResponseDTO getDiary(String email, Date date) {
        List<Diary> diaries = diaryRepository.findAllByUserEmailAndDate(email, date);
        List<MealDTO> meals = diaries.stream().map(diary ->
                new MealDTO(diary.getMeal_type(), diary.getFood())
        ).toList();

        return new DiaryResponseDTO(date, meals);
    }

    public DiaryDTO createCustomDiary(String email, Date date, DiaryRequestDTO diaryRequestDTO) {
        String mealType = diaryRequestDTO.getMealType();
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO(diaryRequestDTO.getName(), diaryRequestDTO.getIngredient_list(),
                Optional.ofNullable(diaryRequestDTO.getImage()).orElse(""),
                Optional.ofNullable(diaryRequestDTO.getRecipe()).orElse(""),
                Optional.ofNullable(diaryRequestDTO.getKcal()).orElse(0.0),
                diaryRequestDTO.getUser_id());
        FoodResponseDTO foodResponseDTO= foodService.createFood(email, foodRequestDTO);
        System.out.println(foodResponseDTO);
        Food food = foodRepository.findById(foodResponseDTO.getId()).orElseThrow(() -> new RuntimeException("food not found"));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Diary newDiary = new Diary(date, mealType, food, user);
        diaryRepository.save(newDiary);
        return new DiaryDTO(newDiary);
    }
}
