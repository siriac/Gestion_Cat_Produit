package org.sid.sec;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 
 * @author nwembetakam
 * OncePerRequestFilter c'est un filtre qui va intervenir pour chaque requete
 * chaque requete qui arrive est ce qu'il contient ce token
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("************************");
		// TODO Auto-generated method stub
		/*
		 * */
		String jwt=request.getHeader("Authorization");
		if(jwt==null) throw new RuntimeException("not Authorized");
		/*
		 * */
		filterChain.doFilter(request, response);
		
	}

	

}
