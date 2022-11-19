package com.group5.btl.controller.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.swap.JoinSwapCreate;
import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapInfo;
import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;
import com.group5.btl.model.SwapWish;
import com.group5.btl.service.JoinSwapService;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.SwapService;
import com.group5.btl.service.SwapWishService;

import java.util.*;

@RestController
@RequestMapping("/swapwish")
public class SwapWishController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SwapService swapService;
	
	@Autowired
	private JoinSwapService joinSwapService;
	
	@Autowired
	private SwapWishService swapWishService;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{id}")
	public List<SwapWishPreview> getListSwapWish(@PathVariable(name = "id") Integer swapId) {
		Swap swap = swapService.getById(swapId);
		SwapInfo swapInfo = swapService.getInfo(swap);
		return swapInfo.getListSwapWishPreview();
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/join")
	public ResponseEntity joinSwap(@RequestBody JoinSwapCreate joinSwap) {
		Student student = studentService.GetById(joinSwap.getUserId());
		SwapWish swapWish = swapWishService.GetSwapWishByID(joinSwap.getSwapWishId());
		joinSwapService.CreateJoinSwap(swapWish, student);
		return ResponseEntity.ok().body(joinSwap);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteJoinSwap(@PathVariable(name="id") Integer swapWishId) {
		joinSwapService.DeleteJoinSwap(joinSwapService.GetByID(swapWishId));
		return ResponseEntity.ok().build();
	}
}
