package com.example.flow.config;

import com.example.flow.entities.User;
import com.example.flow.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Authenticated {

    @Autowired
    private  UserRepo userRepo ;
    @Bean
    public User authenticatedUser(){
        return userRepo.findById(1L).orElseGet(
                () -> {
                    return userRepo.save(
                            User.builder()
                                    .firstName("Nadir")
                                    .lastName("Inab")
                                    .email("nadir@gmail.com")
                                    .numberOfChangeTokens(2)
                                    .hasDeleteToken(true)
                                    .build()
                    ) ;
                }
        ) ;
    }
}
