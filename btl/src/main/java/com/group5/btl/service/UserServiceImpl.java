package com.group5.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.model.User;
import com.group5.btl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSevice{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserRegistrationDto reg) {
		User user = new User(reg.getName(), reg.getEmail(), reg.getPhoneNumber(), reg.getPassword());
		return userRepository.save(user);
	}
}
