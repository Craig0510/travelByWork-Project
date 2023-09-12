package com.example.demo.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public AjaxAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/gethelpermember", "POST"));
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!"application/json".equals(request.getHeader("Content-Type"))) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(request.getReader());
        String username = jsonNode.get("account").asText();
        String password = jsonNode.get("password").asText();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authRequest);
    }
}
