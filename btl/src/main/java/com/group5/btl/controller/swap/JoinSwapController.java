package com.group5.btl.controller.swap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.swap.JoinSwapManage;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.model.Student;
import com.group5.btl.service.JoinSwapService;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.SwapService;

@RestController
@RequestMapping("/joinswap")
public class JoinSwapController {
	
	@Autowired
	private SwapService swapService;
	
	@Autowired
	private JoinSwapService joinSwapService;
	
	@Autowired
	private StudentService studentService;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/manage")
	public List<JoinSwapManage> getListJoinSwapByUserID() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Student student = studentService.findByEmail(userName);
		return joinSwapService.getJoinSwapByUser(student);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteJoinSwap(@PathVariable(name = "id") Integer joinSwapId) {
		joinSwapService.DeleteJoinSwap(joinSwapService.GetByID(joinSwapId));
		return ResponseEntity.ok().build();
	}
}
