package it.epicode.Events.config;

import it.epicode.Events.datalayer.entities.enums.RolesType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

	@Bean
	PasswordEncoder stdPasswordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	AuthTokenFilter authenticationJwtToken() {
		return new AuthTokenFilter();
	}

	@SuppressWarnings("removal")
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, //
			PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class) //
				.userDetailsService(userDetailsService) //
				.passwordEncoder(passwordEncoder) //
				.and().build();
	}

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize ->
				authorize

						.requestMatchers("/api/user/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/user").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/event").hasAuthority(String.valueOf(RolesType.ADMIN))
						.requestMatchers(HttpMethod.PUT, "/api/event").hasAuthority(String.valueOf(RolesType.ADMIN))
						.requestMatchers(HttpMethod.DELETE, "/api/event").hasAuthority(String.valueOf(RolesType.ADMIN))
						.requestMatchers("/**").authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(

						sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationJwtToken(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
