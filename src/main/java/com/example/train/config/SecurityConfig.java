// 落ち着いたらコメントアウトの必要のないコードは削除

package com.example.train.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
// import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
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
                        .mvcMatchers("/general").hasRole("GENERAL")
                        .mvcMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
                return http.build();
    }
    // WebSecurityConfigurerAdapterが非推奨になり、SecurityFilterChainを@Bean定義し使えるように
    // 落ち着くまで下記のコードはコメントアウトしたままに
    // public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http
    //                 .authorizeRequests()
    //                         .antMatchers("/").permitAll()
    //                         .anyRequest().authenticated()
    //                         .and()
    //                 .formLogin()
    //                         .loginPage("/login")
    //                         .permitAll()
    //                         .and()
    //                 .logout()
    //                         .permitAll();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

    // @Bean
    // @Override
    // public UserDetailsService userDetailsService() {
    //     UserDetails user =
    //             User.withDefaultPasswordEncoder()
    //                     .username("user")
    //                     .password("password")
    //                     .roles("USER")
    //                     .build();
    //     return new InMemoryUserDetailsManager(user);
    // }
