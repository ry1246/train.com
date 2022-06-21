package com.example.train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/loginForm")
//                .defaultSuccessUrl("/")
                .failureUrl("/loginForm?error")
                .permitAll()
                )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                )
            .authorizeRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .mvcMatchers("/").permitAll()

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
