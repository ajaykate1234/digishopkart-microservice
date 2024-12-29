package com.digishopkart.product_service.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

// created a custom filter that intercepts HTTP requests to check for a valid JWT.

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTUtil jwtUtil;

    public JwtAuthenticationFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")){
            token = token.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
                if (jwtUtil.validateToken(token,username)){
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(username, null, null));
                }
            }
        }

        chain.doFilter(request, response);
    }
}
