package com.example.ecom.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecom.model.Profile;
import com.example.ecom.model.User;
import com.example.ecom.repository.ProfileRepository;
import com.example.ecom.repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    // Add a profile for the user
    public Profile addProfile(Long userId, Profile profile, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // select * from users where id = 1

        profile.setUser(user); // Associate Profile with the User

        if (file != null && !file.isEmpty()) {
            profile.setProfilePicture(file.getBytes());
        }
        return profileRepository.save(profile);
    }


    // Update a profile for the user
    public Profile updateProfile(Long userId, Profile profile, MultipartFile file) throws IOException {
        Profile existingProfile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        existingProfile.setAddress(profile.getAddress());
        existingProfile.setPhoneNumber(profile.getPhoneNumber());
        if (file != null && !file.isEmpty()) {
            existingProfile.setProfilePicture(file.getBytes());
        }
        return profileRepository.save(existingProfile);
    }
}
