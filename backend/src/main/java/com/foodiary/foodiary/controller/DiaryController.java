package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.DiaryDTO;
import com.foodiary.foodiary.repository.DiaryRepository;
import com.foodiary.foodiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class DiaryController {
//    private final DiaryService diaryService;


    @GetMapping("")
    public String getCalendar() {
        return "calendar";
    }
    @PostMapping("/write/{date}/{meal_type}")
    public  String writeDiary(@PathVariable String date, @PathVariable String meal_type, @RequestBody DiaryDTO diaryDTO) {
        return "success write diary";
    }
}
