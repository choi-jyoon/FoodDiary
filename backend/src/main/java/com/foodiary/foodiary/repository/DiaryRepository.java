package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
    public Optional<Diary> findByName(String name);
}
