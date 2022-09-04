package com.customerService.customerService.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//when there is a request to the back end, there are many filter stages that spring security makes.
//here it's added one more filter stage. it's jwt control filter.
//it's checked if that request was authorized or unauthorized.
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwtToken = extractJwtFromRequest(request);
			if(StringUtils.hasText(jwtToken) && jwtTokenGenerator.validateToken(jwtToken)) {
				Integer id = jwtTokenGenerator.getUserIdFromJwt(jwtToken);
				UserDetails user = userDetailsManager.loadUserById(id);
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					//a local storage, holds all about security like authorized username, password, id
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}catch(Exception e) {
			return;
		}
		filterChain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
			return bearer.substring("Bearer".length() + 1);
		return null;
	}
	
}
