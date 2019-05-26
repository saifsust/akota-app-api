package com.hungry;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean
{




    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;

        
        
        //List<User> lists = userRepository.findAll();
        
        
        
        
      //  System.out.println(lists);
        
         //extract token from header
        final String accessToken = httpRequest.getHeader("header-name");
        
        
       
        
        if (null != accessToken) {
        	
        	
        	//throw new RuntimeException();
        	System.out.println(accessToken);

        }

        chain.doFilter(request, response);
    }

  }
