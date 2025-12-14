package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests(authz -> authz
            // 静的リソースとH2コンソールを全員許可
            .requestMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/webjars/**",
                "/h2-console/**")
            .permitAll()

            // ユーザー登録とログインページも全員許可
            .requestMatchers("/user/signup", "/login").permitAll()

            .anyRequest().authenticated())

        // CSRF対策を無効化
        .csrf(csrf -> csrf.disable());

    // H2コンソールを使用するために、CSRF対策とフレームオプションを無効化（または設定）
    http
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
        // CSRFも無効化
        .csrf(csrf -> csrf.disable());

    return http.build();
  }
}