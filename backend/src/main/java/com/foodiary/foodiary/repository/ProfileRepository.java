package com.foodiary.foodiary.repository;

import com.foodiary.foodiary.entity.Profile;
import com.foodiary.foodiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUser(User user);
}
