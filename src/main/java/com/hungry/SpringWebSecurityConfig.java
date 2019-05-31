package com.hungry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(SpringWebSecurityConfig.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CryptoMaster cryptoMaster;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			LOG.debug(user.toString());
			auth.inMemoryAuthentication().withUser(user.getPhone())
					.password("{noop}" + cryptoMaster.decrypt(user.getPassword())).roles(user.getUserType());

		}

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/user/upload").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/user/login").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/user/public/profile").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/user/admin/profile").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/user/dealer/profile").hasRole("DEALER")
				.antMatchers(HttpMethod.GET, "/user/rider/profile").hasRole("RIDER")
				.antMatchers(HttpMethod.POST, "/order").hasRole("USER").antMatchers(HttpMethod.POST, "/receiver")
				.hasRole("USER").antMatchers(HttpMethod.POST, "/user/registration").anonymous().and().csrf().disable()
				.formLogin().disable();

	}

}
