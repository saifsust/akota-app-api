package com.hungry;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public final class TokenConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		TokenAuthenticationFilter filter = new TokenAuthenticationFilter();
		builder.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);	}

}