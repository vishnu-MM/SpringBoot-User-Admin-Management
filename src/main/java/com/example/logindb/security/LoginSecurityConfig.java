package com.example.logindb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;


@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {

    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public LoginSecurityConfig(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                    configurer
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/dashboard/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin( form ->
                    form
                        .loginPage("/login")
                        .loginProcessingUrl("/UserAuthentication")
                        .successHandler(customSuccessHandler)
                        .permitAll()
                )
                //.logout(LogoutConfigurer::permitAll)
                .logout( logout ->
                    logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID") // Invalidate the session cookie
                        .addLogoutHandler(
                                new HeaderWriterLogoutHandler(
                                        new ClearSiteDataHeaderWriter(COOKIES)
                                )
                        )
                )
                .sessionManagement( session ->
                    session
                        .maximumSessions(2)
                        .maxSessionsPreventsLogin(true)
                )
                .exceptionHandling( exceptionHandling ->
                    exceptionHandling
                        .accessDeniedPage("/access-denied")
                );
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
