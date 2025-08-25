package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.DiaryDTO;
import com.foodiary.foodiary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryDTO getDiary(String id) {
        DiaryDTO dto = new DiaryDTO();
        return dto;
    }
}
