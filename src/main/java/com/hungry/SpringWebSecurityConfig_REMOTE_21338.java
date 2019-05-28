package com.hungry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hungry.repositories.UserRepository;
import com.hungry.services.util.CryptoMaster;

@Configuration

public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CryptoMaster cryptoMaster;
	@Autowired
	private UserRepository userRepository;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	
		
		
		System.out.println();
		
		auth.inMemoryAuthentication()
		.withUser("user")
		.password("{noop}password")
		.roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/").hasRole("USER").and().csrf()
				.disable().formLogin().disable();
	}

}
