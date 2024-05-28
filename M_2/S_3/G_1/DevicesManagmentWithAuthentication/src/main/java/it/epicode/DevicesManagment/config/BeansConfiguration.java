package it.epicode.DevicesManagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BeansConfiguration {

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http //
                .csrf(c -> c.disable()) //
                .authorizeHttpRequests(authorize -> //
                        authorize
                                .anyRequest().permitAll());
        ;

        return http.build();
    }
}
