package com.example.train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                ).logout(logout -> logout
                        .logoutSuccessUrl("/")
                ).authorizeRequests(authz -> authz
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers("/").permitAll()
                        .mvcMatchers("/general").hasRole("GENERAL") // この行は必要なのか要調査
                        .mvcMatchers("/admin").hasRole("ADMIN") // この行は必要なのか要調査
                        
                        .antMatchers("/h2-console/**").permitAll() // /h2-consoleにログイン無しで入れるように(MySQL実装の際に削除)
                        .anyRequest().authenticated()
                )
                .csrf().ignoringAntMatchers("/h2-console/**"); // h2-consoleのCSRF対策(この行がないとConnect押下後ブロックされる)
                http.headers().frameOptions().disable(); // h2-consoleへのlocalhostからの接続を許可するため
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
