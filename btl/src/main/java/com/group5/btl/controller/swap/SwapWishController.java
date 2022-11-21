package com.group5.btl.controller.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;
import com.group5.btl.model.SwapWish;
import com.group5.btl.service.JoinSwapService;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.SwapService;
import com.group5.btl.service.SwapWishService;
import com.group5.btl.service.UserSevice;

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

	@Autowired
	private UserSevice _us;

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{id}")
	public List<SwapWishPreview> getListSwapWish(@PathVariable(name = "id") Integer swapId) {
		Swap swap = swapService.getById(swapId);
		SwapInfo swapInfo = swapService.getInfo(swap);
		return swapInfo.getListSwapWishPreview();
	}

	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/joinswap/{id}")
	public List<UserPreview> getListUserJoinSwap(@PathVariable(name = "id") Integer swapId) {
		return swapWishService.getUserPreviewsJoinSwapWish(swapId);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/join")
	public ResponseEntity joinSwap(@RequestBody JoinSwapCreate joinSwap) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		String userName = authentication.getName();
		UserPreview userPreview = _us.getUserPreviewByEmail(userName);
		

		Student student = studentService.GetById(userPreview.getUserId());
		SwapWish swapWish = swapWishService.GetSwapWishByID(joinSwap.getSwapWishId());

		if (swapWish.getSwapId().getUserId().getId() == student.getId())
			return ResponseEntity.badRequest().body("Không thử tự tham gia môn bạn đăng được!");

		joinSwapService.CreateJoinSwap(swapWish, student);
		joinSwap.setUserId(student.getId());
		return ResponseEntity.ok().body(joinSwap);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/deleteswapwish/{id}")
	public ResponseEntity deleteSwapWish(@PathVariable(name = "id") Integer swapWishId) {
		swapWishService.deleteSwapWishById(swapWishId);
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteJoinSwap(@PathVariable(name = "id") Integer swapWishId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		String userName = authentication.getName();
		UserPreview userPreview = _us.getUserPreviewByEmail(userName);

		joinSwapService.DeleteJoinSwap(joinSwapService.GetByStudentAndSwapWish(userPreview.getUserId(),swapWishId));
		return ResponseEntity.ok().build();
	}
}
