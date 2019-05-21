package com.hungry;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		
		
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}123").roles("USER")
                .and()
                .withUser("admin").password("{noop}123").roles("USER", "ADMIN");

        
        
    }
    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       /* http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();*/
    	
    	//Implementing Token based authentication in this filter
        final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter();
        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);

        //Creating token when basic authentication is successful and the same token can be used to authenticate for further requests
        final CustomBasicAuthenticationFilter customBasicAuthFilter = new CustomBasicAuthenticationFilter(this.authenticationManager() );
        http.addFilter(customBasicAuthFilter);
    	
    }

}
