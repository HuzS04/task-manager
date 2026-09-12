package com.huzaifah.task_manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// this class is the security settings for the whole app
// every request that comes in gets checked against these rules
@Configuration // this is a config class, spring reads it at startup
@EnableWebSecurity // turns on spring security for the app
public class SecurityConfig {

    // this method defines all the security rules
    // spring automatically applies these rules to every request
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // csrf is a type of attack on cookie-based apps
                // api uses jwt tokens, not cookies so this protection isnt needed as it would just block requests for no reason
                .csrf(csrf -> csrf.disable())

                // normally a server remembers who you are between requests using sessions
                // jwt doesnt need this cause every request carries its own token
                // so i set it to stateless meaning no sessions are created or stored
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // this is where i decide who can access what endpoints
                // for now im letting everyone through while i set up jwt
                // later ill change this to block everything except /auth/login
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        // packages all the rules above and applies them to every request
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
