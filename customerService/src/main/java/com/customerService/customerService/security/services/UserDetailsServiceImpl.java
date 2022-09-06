package com.customerService.customerService.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.customerService.customerService.dataAccess.abstracts.UserDao;
import com.customerService.customerService.entities.concretes.User;


//UserDetailsService interface has a method to load User by username
//and returns a UserDetails object that Spring Security can use for authentication and validation.
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}

}
