package com.javainterns.bookingroom.security;

import com.javainterns.bookingroom.security.filters.JwtAuthenticationFilter;
import com.javainterns.bookingroom.security.filters.JwtAuthorizationFilter;
import com.javainterns.bookingroom.security.jwt.JwtUtils;
import com.javainterns.bookingroom.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig {


    private final JwtUtils jwtUtils;


    private final UserDetailsServiceImpl userDetailsService;


    private final JwtAuthorizationFilter authorizationFilter;

    public SecurityConfig(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, JwtAuthorizationFilter authorizationFilter) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            AuthenticationManager authenticationManager
    ) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
                jwtUtils
        );
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/h2-console/", "/h2-console/**").permitAll();
                    auth
                            .requestMatchers(
                                    "/",
                                    "/error",
                                    "/csrf",
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/v3/api-docs",
                                    "/api-docs",
                                    "/api-docs/**",
                                    "/v3/api-docs/**"
                            )
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/users").permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(
                        authorizationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            HttpSecurity httpSecurity,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
