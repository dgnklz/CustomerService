package com.customerService.customerService.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customerService.customerService.dataAccess.abstracts.UserDao;
import com.customerService.customerService.entities.concretes.User;

@Service
public class UserDetailsManager implements UserDetailsService{
	
	private UserDao userDao;
	
	public UserDetailsManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(int id) {
		User user = userDao.findById(id).get();
		return JwtUserDetails.create(user);
	}
	
}
