package com.edward.restproject.universalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/error", "/h2-console/**", "/standup/**").permitAll()
                .anyRequest().permitAll()
            )
            .headers(headers -> headers.frameOptions(f -> f.disable())); // needed for H2 console
        return http.build();
    }
}
