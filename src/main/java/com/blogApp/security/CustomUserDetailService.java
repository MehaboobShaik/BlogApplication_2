package com.blogApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blogApp.Repository.UserRepository;
import com.blogApp.customexceptions.ResourceNotFoundException;
import com.blogApp.models.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User findByEmail = null;
		try {
			findByEmail = userRepository.findByEmail(username);
		} catch (ResourceNotFoundException e) {
			e.getMessage();
		}
		return findByEmail;
	}

}
