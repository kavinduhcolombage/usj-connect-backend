package com.USJ.UniConnect_Backend;

import com.USJ.UniConnect_Backend.jwt.JwtAuthenticationEntryPoint;
import com.USJ.UniConnect_Backend.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtAuthenticationEntryPoint point;

        @Autowired
        private JwtAuthenticationFilter filter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                /*
                 * http.csrf(csrf -> csrf.disable())
                 * .authorizeHttpRequests(auth -> auth
                 * .requestMatchers("/api/v1/auth/login").permitAll()
                 * .anyRequest().authenticated()
                 * )
                 * .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                 * .sessionManagement(session ->
                 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                 * 
                 * http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                 * 
                 * return http.build();
                 */

                http.cors()
                                .and()
                                .csrf(csrf -> csrf.disable())
                                .authorizeRequests()
                                .requestMatchers("/api/v1/auth/login", "api/v1/usercontroller/signup").permitAll()
                                .anyRequest()
                                .authenticated()
                                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                return http.build();

        }
}
