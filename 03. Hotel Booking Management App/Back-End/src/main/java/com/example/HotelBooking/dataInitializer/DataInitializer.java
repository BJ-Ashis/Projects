package com.example.HotelBooking.dataInitializer;

import com.example.HotelBooking.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.HotelBooking.enums.UserRole;
import com.example.HotelBooking.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        if (userRepository.findByEmail("devilswar777@gmail.com").isEmpty()) {
            User admin = User.builder()
                    .firstName("Ashis")
                    .lastName("Panda")
                    .email("devilswar777@gmail.com")
                    .password(passwordEncoder.encode("ashis"))
                    .phoneNumber("1234567890")
                    .role(UserRole.ADMIN)
                    .isActive(true)
                    .build();
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("deadlyempire666@gmail.com").isEmpty()) {
            User customer = User.builder()
                    .firstName("Dunu")
                    .lastName("Panda")
                    .email("deadlyempire666@gmail.com")
                    .password(passwordEncoder.encode("dunu"))
                    .phoneNumber("0987654321")
                    .role(UserRole.CUSTOMER)
                    .isActive(true)
                    .build();
            userRepository.save(customer);
        }
        return null;
    }

}