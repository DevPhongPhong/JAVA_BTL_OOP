package com.btlptit.demo.service;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.btlptit.demo.dto.UserRegistrationDto;
import com.btlptit.demo.model.Role;
import com.btlptit.demo.model.User;
import com.btlptit.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserRegistrationDto reg) {
		User user = new User(reg.getName(), reg.getEmail(), reg.getStudentId(), reg.getPassword(), Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
