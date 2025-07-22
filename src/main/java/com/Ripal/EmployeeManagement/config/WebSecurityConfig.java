package com.Ripal.EmployeeManagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final RestAuthenticationEntryPoint entryPoint;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain filterChanin(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable()).authorizeRequests()
                .requestMatchers("/async/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(
                        config -> config.authenticationEntryPoint(entryPoint)
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication().withUser("testRipal").password(passwordEncoder.encode("Ripal@123")).roles("ADMIN")
                .and()
                .withUser("testUser").password(passwordEncoder.encode("User@123")).roles("USER");
        return authenticationManagerBuilder.build();

    }
}
