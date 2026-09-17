package com.huzaifah.task_manager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

// OncePerRequestFilter — runs exactly once per request
// this is where every incoming request gets checked for a valid JWT token
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // read the Authorization header from the request
        // should look like: "Bearer eyJhbGciOiJIUzI1NiJ9..."
        String authHeader = request.getHeader("Authorization");

        // if there's no Authorization header or it doesn't start with "Bearer "
        // just pass the request through — no token means not authenticated
        // Spring Security will handle blocking it if the endpoint requires auth
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // strip the "Bearer " prefix to get just the token
        String token = authHeader.substring(7);

        // validate the token — checks signature and expiry
        if (jwtUtil.isTokenValid(token)) {

            // extract the username (email) from the token
            String username = jwtUtil.extractUsername(token);

            // create an authentication object Spring Security understands
            // this tells Spring "this user is authenticated"
            // empty ArrayList means no specific roles/authorities for now
            UserDetails userDetails = new User(username, "", new ArrayList<>());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // put the authentication into the security context
            // this is how Spring Security knows the current user for this request
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // pass the request on to the next filter or the Controller
        filterChain.doFilter(request, response);
    }
}