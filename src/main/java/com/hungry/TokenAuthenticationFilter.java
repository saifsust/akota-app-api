package com.hungry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;


public final class TokenAuthenticationFilter extends GenericFilterBean
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
	
		HttpServletRequest req =(HttpServletRequest)request;
		
		
		System.out.println(req.getHeader("name"));
		
		
	}
	
	

	


}
