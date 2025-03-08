package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Fix: Disable CSRF for Swagger/Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/register",
                                "/login",
                                "/auth/forgotPassword/**",
                                "/auth/resetPassword/**"   // ✅ Fix: Allow reset password without login
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable()) // ✅ Disable default login form (not needed for API)
                .httpBasic(httpBasic -> httpBasic.disable()); // ✅ Disable basic auth if not required

        return http.build();
    }
}
