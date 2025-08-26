package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.DiaryDTO;
import com.foodiary.foodiary.dto.DiaryRequestDTO;
import com.foodiary.foodiary.dto.DiaryResponseDTO;
import com.foodiary.foodiary.dto.FoodRequestDTO;
import com.foodiary.foodiary.repository.DiaryRepository;
import com.foodiary.foodiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/{date}")
    public DiaryResponseDTO getCalendar(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return diaryService.getDiary(email, date);
    }
    @PostMapping("/{date}/write/custom")
    public  String writeDiary(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestBody DiaryRequestDTO diaryRequestDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        diaryService.createCustomDiary(email, date, diaryRequestDTO);
        return "Success write diary";
    }
}
