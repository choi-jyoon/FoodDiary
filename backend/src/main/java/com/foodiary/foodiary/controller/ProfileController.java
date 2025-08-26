package com.foodiary.foodiary.controller;

import com.foodiary.foodiary.dto.ProfileDTO;
import com.foodiary.foodiary.entity.Foodthema;
import com.foodiary.foodiary.entity.User;
import com.foodiary.foodiary.repository.UserRepository;
import com.foodiary.foodiary.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/")
    public ProfileDTO getProfile() {
        System.out.println("SecurityContext Auth: " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("SecurityContext Auth: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        System.out.println("SecurityContext Auth: " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("SecurityContext Auth finish");
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (email.equals("anonymousUser")) {throw new RuntimeException("User not logged in");}
        return profileService.getProfileByUser(email);
    }

    @PostMapping("/create")
    public String createProfile(@RequestBody ProfileDTO profileDTO) {

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) { throw new RuntimeException("User not logged in"); }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        profileService.createUserProfile(email, profileDTO.getNickname(), profileDTO.getWeight(), profileDTO.getHeight(), profileDTO.getGoalweight(), profileDTO.getFoodthemaId());
        return "success create profile";
    }

    @PutMapping("/edit")
    public String editProfile(@RequestBody ProfileDTO profileDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long foodthemaId = profileDTO.getFoodthemaId() != null
                ? profileDTO.getFoodthemaId()
                : 0L;
        profileService.updateUserProfile(email, profileDTO.getNickname(), profileDTO.getWeight(), profileDTO.getHeight(), profileDTO.getGoalweight(), foodthemaId);
        return "success edit profile";
    }
}
