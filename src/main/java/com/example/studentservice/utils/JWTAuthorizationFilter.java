package com.example.studentservice.utils;

import com.example.studentservice.helpers.SpringContextHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {


@Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().matches("/pjese(.*)")
//                || request.getServletPath().matches("/employee(.*)")
                || request.getServletPath().matches("/user(.*)")

                || request.getServletPath().matches("/admin(.*)")

                || request.getServletPath().matches("/task(.*)")

                || request.getServletPath().matches("/cleaner(.*)")

                || request.getServletPath().matches("/(property.*)")
        ) {
            filterChain.doFilter(request, response);
        } else {
            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            JwtUtils tokenUtils = SpringContextHelper.getApplicationContext().getBean(JwtUtils.class);
            String username;
            String token;
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                token = tokenHeader.substring(7);
                try {
                    username = tokenUtils.getSubject(token);
                    if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
                        if (tokenUtils.validateAccessToken(token)) {
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    username, null,
                                    tokenUtils.getAuthorities(token));
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                    filterChain.doFilter(request, response);
                } catch (IllegalArgumentException e) {
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                } catch (ExpiredJwtException e) {
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    Map<String, String> map = new HashMap<>();
                    map.put("error_msg", e.getMessage());
                    new ObjectMapper().writeValue(response.getOutputStream(), map);
                }

            } else {
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                Map<String, String> map = new HashMap<>();
                map.put("error", "UNAUTHORIZED");
                new ObjectMapper().writeValue(response.getOutputStream(), map);
            }
        }

    }

}
