package com.group5.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group5.btl.service.RoleService;

@Controller
@RequestMapping("home")
public class HomeController {
	
    @GetMapping()
    public String Index() {
        return "home/index";
    }
}
