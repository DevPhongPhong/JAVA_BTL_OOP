package com.group5.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group5.btl.repository.UserRepository;
import com.group5.btl.service.CourseService;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.sector.SectorService;
import com.group5.btl.service.SwapService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
    private SwapService _ss;

	@Autowired
	private CourseService courseService;
	
    @GetMapping()
    public String Index(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication != null) {
    		model.addAttribute("user", authentication.getName());    		
    	}
        return "home/index";
    }
}
