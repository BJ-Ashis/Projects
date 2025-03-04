package com.example.spring.service;

import com.example.spring.dto.ProfileDTO;

public interface ProfileService {

    /**
     * It will save the user details to database
     * @param profileDTO
     * @return profileDto
     * */
    ProfileDTO createProfile(ProfileDTO profileDTO);
}
