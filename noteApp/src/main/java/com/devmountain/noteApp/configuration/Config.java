package com.devmountain.noteApp.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
 Part 3e: Creating Config class
    -- @Configuration

    -- create a custom Bean so the Spring can keep track of it and the application can use it for
        Dependency Injection
        -- private method of type PasswordEncoder, annotate it with Bean
            -- return a new BCryptPasswordEncoder()

*/
