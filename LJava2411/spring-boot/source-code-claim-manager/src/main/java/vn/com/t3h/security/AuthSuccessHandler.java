package vn.com.t3h.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vn.com.t3h.service.LoginService;

import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Get username from authentication object
        UserDetails userDetails = ((User) authentication.getPrincipal());

        // Create JWT token
        String token = jwtTokenUtil.generateToken(userDetails);

        // Add token to session or response cookies (here we add it to the response as a cookie)
        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true); // Security feature to prevent JS access to the cookie
        cookie.setSecure(true); // Use only on HTTPS connections
        cookie.setPath("/"); // Available across the entire application
        cookie.setMaxAge(24 * 60 * 60); // 1 ngày
        response.addCookie(cookie);

        // Redirect to appropriate page based on role
        String redirectUrl = loginService.processAfterLoginSuccess();
        response.sendRedirect(redirectUrl);
    }


}
