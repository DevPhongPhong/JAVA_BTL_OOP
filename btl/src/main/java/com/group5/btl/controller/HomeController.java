package com.group5.btl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;
import com.group5.btl.repository.UserRepository;
import com.group5.btl.service.CourseService;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.sector.SectorService;


import com.group5.btl.service.SwapService;
import com.group5.btl.service.UserSevice;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
    private SwapService _ss;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserSevice userSevice;
	
	@Autowired
	private StudentService studentService;
	
    @GetMapping()
    public String Index(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication instanceof AnonymousAuthenticationToken) {
    		HashMap<String, String> user = new HashMap<>();
    		user.put("username", "anonymousUser");
    		user.put("id", "-1");
    		model.addAttribute("user", user);    		
    	} else {
    		String userName = authentication.getName();
    		UserPreview userPreview = userSevice.getUserPreviewByEmail(userName);
    		HashMap<String, String> user = new HashMap<>();
    		user.put("username", userPreview.getName());
    		user.put("id", String.valueOf(userPreview.getId()));
    		model.addAttribute("user", user);
    		Student student = studentService.GetById(userPreview.getId());
    		List<Swap> listSwap = student.getListSwaps();
    		List<String> idSwapList = new ArrayList<>();
    		for(Swap xSwap : listSwap) {
    			idSwapList.add(String.valueOf(xSwap.getId()));
    		}
    		model.addAttribute("swaplist", idSwapList);
    	
    	}
        return "home/index";
    }
}
