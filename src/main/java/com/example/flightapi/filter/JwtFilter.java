package com.example.flightapi.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.flightapi.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // 排除 H2, Swagger 和公开接口
        return path.contains("swagger")
                || path.contains("api-docs")
                || path.contains("webjars")
                || path.contains("/h2-console");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                JwtUtil.validateToken(token.substring(7));
                String userId = JwtUtil.getUserIdFromToken(token.substring(7));
                // 将用户信息存入SecurityContext
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userId, null, List.of(new SimpleGrantedAuthority("USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, int code, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(code);
        response.getWriter().write(
                String.format("{\"error\":\"%s\",\"message\":\"%s\"}",
                        code == 401 ? "UNAUTHORIZED" : "FORBIDDEN",
                        message));
    }
}
