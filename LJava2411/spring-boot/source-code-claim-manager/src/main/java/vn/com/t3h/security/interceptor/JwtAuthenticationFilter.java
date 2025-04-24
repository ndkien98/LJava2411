package vn.com.t3h.security.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.t3h.security.JwtTokenUtil;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/process_login") ||
                request.getRequestURI().contains("/login") ||
                request.getRequestURI().contains("/logout") ||
                request.getRequestURI().contains("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String token = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (!StringUtils.hasText(token) || !jwtTokenUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized: Invalid or missing token\"}");
            return;
        }
        chain.doFilter(request, response);
    }
}

