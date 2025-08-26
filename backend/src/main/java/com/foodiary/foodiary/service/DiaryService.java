package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.DiaryDTO;
import com.foodiary.foodiary.entity.Diary;
import com.foodiary.foodiary.entity.Food;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.DiaryRepository;
import com.foodiary.foodiary.repository.FoodRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public DiaryDTO getDiary(String id) {
        DiaryDTO diaryDTO = new DiaryDTO();
        return diaryDTO;
    }

    public DiaryDTO createDiary(DiaryDTO diaryDTO) {
        Food food = foodRepository.findById(diaryDTO.getFood_id()).orElseThrow(() -> new UsernameNotFoundException("Food not found"));
        User user = userRepository.findById(diaryDTO.getUser_id()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Diary newDiary = new Diary(diaryDTO.getDate(), diaryDTO.getMeal_type(), food, user);
        diaryRepository.save(newDiary);
        return new DiaryDTO(newDiary);
    }
}
