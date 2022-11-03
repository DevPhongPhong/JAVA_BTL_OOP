package com.group5.btl.service;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.model.User;

public interface UserSevice{
	User save(UserRegistrationDto userRegistrationDto);
	User findByEmail(String email);
}
