package com.group5.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group5.btl.service.RoleService;
import com.group5.btl.service.UserSevice;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserSevice userSevice;
	
	@GetMapping
	public String showRole(Model model) {
		model.addAttribute("roles", userSevice.findByEmail("honghm211@gmail.com"));
		return "home/index";
	}
}
