package com.btlptit.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.btlptit.demo.dto.UserRegistrationDto;
import com.btlptit.demo.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
