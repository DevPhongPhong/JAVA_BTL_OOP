package com.group5.btl.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.service.UserSevice;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UserSevice userSevice;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/get/{id}")
	public UserPreview getUserPreview(@PathVariable(name = "id") Integer userId) {
		return userSevice.getUserPreview(userId);
	}
}
