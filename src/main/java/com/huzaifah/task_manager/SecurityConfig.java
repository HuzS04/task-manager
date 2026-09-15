package com.huzaifah.task_manager;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// this class defines all the security rules for the whole application
// every request passes through these rules before reaching any Controller
@Configuration // tells Spring this is a configuration class, read it at startup
@EnableWebSecurity // activates Spring Security for the whole application
public class SecurityConfig {

    // inject the JWT filter so we can add it to the filter chain
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // defines the security rules — which endpoints are public and which require a token
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // turn off CSRF — not needed for REST APIs using JWT tokens instead of cookies
                .csrf(csrf -> csrf.disable())

                // dont use sessions — JWT handles authentication on every request instead
                // each request carries its own token, server doesnt remember anything between requests
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // define which endpoints are public and which need a valid JWT token
                .authorizeHttpRequests(auth -> auth
                        // these two endpoints are public — anyone can call them without a token
                        // makes sense because you need to login to GET a token in the first place
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        // everything else requires authentication
                        // if the JWT filter didnt verify a valid token, Spring blocks the request with 401
                        .anyRequest().authenticated()
                )

                // when an unauthenticated request hits a protected endpoint
                // instead of redirecting to a login page (which makes no sense for a REST API)
                // return a proper JSON 401 response
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint())
                )

                // add our JWT filter BEFORE Springs default authentication filter
                // so our filter runs first, reads and validates the token
                // then sets the authenticated user in the security context
                // then Springs filter runs and checks if that user is allowed through
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // this runs when someone hits a protected endpoint without a valid token
    // instead of Springs default HTML redirect, this returns clean JSON
    // so the API caller knows exactly what went wrong
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // set HTTP status to 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // tell the caller the response is JSON not HTML
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            // write the actual JSON error message
            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Valid JWT token required\"}");
        };
    }

    // BCrypt password encoder — used to hash passwords before storing
    // and to check passwords on login
    // Spring injects this wherever PasswordEncoder is needed
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}