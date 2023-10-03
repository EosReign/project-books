package com.eosreign.projectbooks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/login", "/register",
            "/book/", "/swagger-resources/**",
            "/v3/api-docs", "/webjars/**",
            "/book",
            "/book/get_books", "/book/get_by_fulltext",
            "/book/get_books_by_author", "/book/get_books_by_publisher",
            "/book/get"
    };
    //TODO delete after TESTING
    private static final String[] TEST_WHITELIST = {
         "/client/**", "/transaction/**"
    };

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) ->
                        authz
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers(TEST_WHITELIST).permitAll()
                                .requestMatchers("/book/**").authenticated()
                                .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

}
