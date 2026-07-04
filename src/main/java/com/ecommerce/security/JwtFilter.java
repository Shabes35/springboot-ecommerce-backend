package com.ecommerce.security;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import  org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public JwtFilter(JwtService jwtService , UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");


        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request , response);
            return;
        }

        String token = authHeader.substring(7);

        if(jwtService.isTokenValid(token)){
            String email = jwtService.extractEmail(token);

            User user = userRepository.findByEmail(email);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user , null, null);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
