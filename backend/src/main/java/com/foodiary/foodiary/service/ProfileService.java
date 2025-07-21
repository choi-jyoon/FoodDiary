package com.foodiary.foodiary.service;

import com.foodiary.foodiary.dto.ProfileDTO;
import com.foodiary.foodiary.entity.Foodthema;
import com.foodiary.foodiary.entity.Profile;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.FoodthemaRepository;
import com.foodiary.foodiary.repository.ProfileRepository;
import com.foodiary.foodiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FoodthemaRepository foodthemaRepository;

    public ProfileDTO getProfileByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Profile profile = profileRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Profile not yet created"));
        Long foodthemaId = null;
        if (profile.getFoodthema()!=null) {
            foodthemaId = profile.getFoodthema().getId();
        }
        return new ProfileDTO(profile.getNickname(), profile.getWeight(), profile.getHeight(), profile.getGoal_weight(), foodthemaId);
    }

    public ProfileDTO createUserProfile(String email, String nickname, double weight, double height, double goal_weight, Long foodthemaId ) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (profileRepository.findByUser(user).isPresent()) {
            throw new RuntimeException("User Profile already exists");
        }
        Profile profile = new Profile();
        profile.setNickname(nickname);
        profile.setWeight(weight);
        profile.setHeight(height);
        profile.setGoal_weight(goal_weight);
        profile.setUser(user);
        if (foodthemaRepository.findById(foodthemaId).isPresent()) {
            profile.setFoodthema(foodthemaRepository.findById(foodthemaId).get());
        }
        else {
            profile.setFoodthema(null);
        }
        profileRepository.save(profile);
        Optional<Foodthema> foodthemaOpt = foodthemaRepository.findById(foodthemaId);
        profile.setFoodthema(foodthemaOpt.orElse(null));

        Long themaId = foodthemaOpt.map(Foodthema::getId).orElse(null);

        return new ProfileDTO(profile.getNickname(), profile.getWeight(), profile.getHeight(), profile.getGoal_weight(), themaId);
    }

    public ProfileDTO updateUserProfile(String email, String nickname, double weight, double height, double goal_weight, Long foodthemaId ) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Foodthema newfoodthema = null;
        Long newFoodthemaId = null;
        if (profileRepository.findByUser(user).isPresent()) {
            Profile profile = profileRepository.findByUser(user).get();

            if (foodthemaId != null) {
                newfoodthema = foodthemaRepository.findById(foodthemaId).orElse(null);
            }
            if (newfoodthema != null) {newFoodthemaId = newfoodthema.getId();}
        }
        else {
            throw new RuntimeException("User Profile does not exist");
        }

        Profile profile = profileRepository.findByUser(user).get();
        profile.updateProfile(nickname, weight, height, goal_weight, newfoodthema);
        profileRepository.save(profile);

        return new ProfileDTO(profile.getNickname(), profile.getWeight(), profile.getHeight(), profile.getGoal_weight(), newFoodthemaId);
    }

}
