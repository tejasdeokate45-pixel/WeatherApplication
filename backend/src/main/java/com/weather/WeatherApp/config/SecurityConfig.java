package com.weather.WeatherApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Required to allow requests from your frontend
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/weather/**").permitAll() // Open the "Weather" door for everyone
                        .anyRequest().authenticated()               // Lock all other doors (like /admin)
                )
                .formLogin(form -> form.permitAll()); // Keep the login page for the locked doors

        return http.build();
    }
}