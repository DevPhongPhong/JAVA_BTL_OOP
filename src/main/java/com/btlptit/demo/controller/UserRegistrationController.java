package com.btlptit.demo.controller;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.btlptit.demo.dto.UserRegistrationDto;
import com.btlptit.demo.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	private String showRegistrationPage(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}
	
	@PostMapping
	private String registrationAccount(@ModelAttribute("user") UserRegistrationDto reg) {
		try {
			userService.save(reg);
		} catch (Exception e) {
			return "redirect:/registration?error";
		}
		return "redirect:/registration?success";
	}
}
