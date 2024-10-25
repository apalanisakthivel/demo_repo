package com.example.ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
