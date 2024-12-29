package com.digishopkart.product_service.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTUtil jwtUtil;

    public SecurityConfig(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    // Defined in-memory authentication for simplicity (can be replaced with DB-based)
    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password("{nope}password").roles("USER").build()
        );
    }

    // JWT Authentication Filter
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter(jwtUtil);
//    }

    // JWT Authentication Filter
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager);  // Set the authentication manager
        return filter;
    }

    // Define AuthenticationManager bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService());
        return authenticationManagerBuilder.build();
    }

    // Configure HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/digi/**").permitAll()  // Public endpoints
                .anyRequest().authenticated()  // Secure all other endpoints
                .and()
                .addFilterBefore(jwtAuthenticationFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before the default filter
               // .csrf().disable();  // Disable CSRF protection for stateless APIs

        return http.build();
    }
}
