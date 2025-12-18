package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

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
        // .csrf(csrf -> csrf.disable());

    // ログイン処理
    http
        .formLogin(form -> form
            .loginProcessingUrl("/login") // ログイン処理のURL
            .loginPage("/login") // ログインページのURL
            .defaultSuccessUrl("/user/list", true) // ログイン成功後のリダイレクト先
            .failureUrl("/login?error") // ログイン失敗時のリダイレクト先
            .usernameParameter("userId") // ログインページのユーザーIDのパラメータ名
            .passwordParameter("password") // ログインページのパスワードのパラメータ名
            .permitAll());

    // ログアウト処理
    http
        .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理のURL
            .logoutSuccessUrl("/login?logout") // ログアウト成功後のリダイレクト先
            .permitAll());

    return http.build();
  }
}