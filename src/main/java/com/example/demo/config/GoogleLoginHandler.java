package com.example.demo.config;

import com.example.demo.dto.GoogleLoginPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleLoginHandler {

    public Object googleLoginPrincipal(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oAuth2AuthenticationToken =(OAuth2AuthenticationToken) authentication;
            OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
            String mapName = oAuth2User.getAttribute("name");
            String mapEmail = oAuth2User.getAttribute("email");
            String mapLocal = oAuth2User.getAttribute("locale");
            GoogleLoginPrincipal googleLoginPrincipal = new GoogleLoginPrincipal();
            googleLoginPrincipal.setName(mapName);
            googleLoginPrincipal.setEmail(mapEmail);
            googleLoginPrincipal.setLocale(mapLocal);
            return googleLoginPrincipal;
        }else{
            return null;
        }

    }

}
