package com.example.spring.service.impl;

import com.example.spring.dto.ProfileDTO;
import com.example.spring.entity.ProfileEntity;
import com.example.spring.exceptions.ItemExistsException;
import com.example.spring.repository.ProfileRepository;
import com.example.spring.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder encoder;

    /**
     * It will save the user details to database
     * @param profileDTO
     * @return profileDto
     * */
    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        if (profileRepository.existsByEmail(profileDTO.getEmail())) {
            throw new ItemExistsException("Profile already exists "+profileDTO.getEmail());
        }
        profileDTO.setPassword(encoder.encode(profileDTO.getPassword()));
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        profileEntity = profileRepository.save(profileEntity);
        log.info("Printing the profile entity details {}", profileEntity);
        return mapToProfileDTO(profileEntity);
    }

    /**
     * Mapper method to map values from profile entity to profile dto
     * @param profileEntity
     * @return profileDto
     * */
    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profile dto to profile entity
     * @param profileDTO
     * @return profileEntity
     * */
    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
