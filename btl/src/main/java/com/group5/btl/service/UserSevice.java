package com.group5.btl.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.model.User;

public interface UserSevice extends UserDetailsService{
	User save(UserRegistrationDto userRegistrationDto);
	User findByEmail(String email);
	UserPreview getUserPreview(int id);
}
