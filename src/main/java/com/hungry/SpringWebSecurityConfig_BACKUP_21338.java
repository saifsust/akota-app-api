package com.hungry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.services.util.CryptoMaster;

@Configuration
@Component
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CryptoMaster cryptoMaster;


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			System.out.print(user);
			auth.inMemoryAuthentication().withUser(user.getPhone())
					.password("{noop}" + cryptoMaster.decrypt(user.getPassword())).roles("USER");

		}
		// auth.authenticationProvider(tokenAuthenticationProvider);

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/order").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/receiver").hasRole("USER")
		.and()
        .csrf().disable()
        .formLogin().disable();
		
	}

	


}
