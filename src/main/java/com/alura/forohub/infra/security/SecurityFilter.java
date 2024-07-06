package com.alura.forohub.infra.security;

import com.alura.forohub.commons.constants.ApiConstants;
import com.alura.forohub.domain.login.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el token
        var token = request.getHeader(ApiConstants.AUTHORIZATION);
        if(token != null) {
            token = token.replace(ApiConstants.BEARER_TOKEN, ApiConstants.EMPTY_STRING);
            var subject = tokenService.getSubject(token);
            if (subject != null){
                // Token válido
                var user = userRepository.findByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
            filterChain.doFilter(request, response);
    }
}
