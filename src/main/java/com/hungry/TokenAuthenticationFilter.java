package com.hungry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean
{


    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

         //extract token from header
        final String accessToken = httpRequest.getHeader("header-name");
        if (null != accessToken) {
        	
        	
        	//throw new RuntimeException();
        	System.out.println(accessToken);
        	
       //get and check whether token is valid ( from DB or file wherever you are storing the token)

      //Populate SecurityContextHolder by fetching relevant information using token
          /* final User user = new User(
                        "username",
                        "password",
                        true,
                        true,
                        true,
                        true,
                        null);
           
           
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);*/

        }

        chain.doFilter(request, response);
    }

  }
