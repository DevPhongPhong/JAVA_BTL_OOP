package com.group5.btl.controller;

import java.io.Console;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group5.btl.dto.UserRegistrationDto;
import com.group5.btl.service.UserSevice;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
	private UserSevice userSevice;
	
	@GetMapping()
	public String registration(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "login/registration";
	}
	
	@PostMapping
	public String registrationAccount(@ModelAttribute("user") UserRegistrationDto reg) {
		try {
			userSevice.save(reg);
		} catch (Exception e) {
			return "redirect:/registration?error";
		}
		return "redirect:/registration?success";
	}
}
