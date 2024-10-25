package com.example.ecom.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecom.model.Profile;
import com.example.ecom.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // add new profile for user
    @PostMapping("/addProfile")
    @PreAuthorize("hasRole('USER')|| hasRole('ADMIN')")
    public ResponseEntity<Profile> createProfile(
            @RequestParam("userId") Long userId, // Add userId parameter
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("file") MultipartFile file) {

        Profile profile = new Profile();
        profile.setAddress(address);
        profile.setPhoneNumber(phoneNumber);

        try {
            Profile savedProfile = profileService.addProfile(userId, profile, file);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update a profile for the user
    @PutMapping("/updateProfile")
    @PreAuthorize("hasRole('USER')|| hasRole('ADMIN')")
    public ResponseEntity<Profile> updateProfile(
            @RequestParam("userId") Long userId,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("file") MultipartFile file) {
        Profile profile = new Profile();
        profile.setAddress(address);
        profile.setPhoneNumber(phoneNumber);
        try {
            Profile updatedProfile = profileService.updateProfile(userId, profile, file);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
