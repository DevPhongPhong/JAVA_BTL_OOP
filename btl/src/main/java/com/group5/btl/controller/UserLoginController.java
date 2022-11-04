package com.group5.btl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserLoginController {
	@GetMapping()
	public String loginPage() {
		return "login/login";
	}
	
}
