package com.example.userroleauth.config;

import com.example.userroleauth.auth.JwtTokenValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfig = new CorsConfiguration();
                        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
                        corsConfig.setAllowedMethods(Collections.singletonList("*"));
                        corsConfig.setAllowCredentials(true);
                        //corsConfig.setExposedHeaders(List.of("Authorization"));
                        corsConfig.setMaxAge(3600L);
                        return null;
                    }
                })
                .and()
                .csrf()
                .disable()
                //.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth/authentication/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user-role-auth-service/users/**").hasAnyAuthority("User-FullAccess", "User-ReadOnly")
                .antMatchers("/user-role-auth-service/users/**").hasAuthority("User-FullAccess")
                .antMatchers("/user-role-auth-service/permissions/**").hasAuthority("Admin-FullAccess")
                .antMatchers(HttpMethod.GET, "user-role-auth-service/roles/**").hasAnyAuthority("Role-ReadOnly", "Role-FullAccess")
                .antMatchers("/user-role-auth-service/roles/**").hasAuthority("Role-FullAccess")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
