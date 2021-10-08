package com.vhais.practice.tacocloud.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vhais.practice.tacocloud.tacos.User;
import com.vhais.practice.tacocloud.tacos.data.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeRequests()
					.antMatchers("/design", "/orders").hasRole("USER")
					.antMatchers("/", "/**").permitAll()
				.and().csrf().disable()
					.formLogin().loginPage("/login").defaultSuccessUrl("/design")//.successForwardUrl("/design")
				.and()
					.logout()
				.and()
					.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			log.info("username: " + username);
			User user = userRepo.findByUsername(username);
			if (user != null) return user;

			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}
}
