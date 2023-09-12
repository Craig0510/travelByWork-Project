package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthenticationConfiguration authConfiguration;

    public SecurityConfig(AuthenticationConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain projectSecurityConfig(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .securityContext(securityContext -> securityContext.requireExplicitSave(false))
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(300))
                .addFilterBefore(new AjaxAuthenticationFilter(this.authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll()).formLogin()
                .loginPage("/gethelpermember")
                .usernameParameter("account")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/helperAccountAfter.html")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutUrl("/clearSession")
                .logoutSuccessUrl("/homePage.html")
                .permitAll();

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
