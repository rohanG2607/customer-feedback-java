package com.feedback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.feedback.security.CustomAuthenticationSuccessHandler;
import com.feedback.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final CustomAuthenticationSuccessHandler successHandler;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder,
			CustomAuthenticationSuccessHandler successHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.successHandler = successHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.authenticationProvider(authenticationProvider())

				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**")
								.permitAll()
									.requestMatchers("/admin/**").hasRole("ADMIN")
								.requestMatchers("/dashboard", "/feedback/**").hasRole("USER")
								.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").successHandler(successHandler)
						.permitAll())
				.exceptionHandling(exception ->
		        exception.accessDeniedPage("/403"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}

}