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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.btl.dto.PagingDto;
import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapCreateFromView;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.service.CourseService;
import com.group5.btl.service.SwapService;
import com.group5.btl.service.UserSevice;

import java.util.*;

@RestController
@RequestMapping("/swap")
public class SwapController {
	@Autowired
	private SwapService swapService;

	@Autowired
	private CourseService _cs;

	@Autowired
	private UserSevice _us;

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/get/{id}")
	public List<SwapPreview> getListSwapByCourseID(@PathVariable(name = "id") Integer courseId) {
//		List<SwapPreview> list = swapService.getByCourseId(courseId);
//		return new PagingDto<SwapPreview>(1, list.size() / 3 + 1, list.subList(0,3));
		return swapService.getByCourseId(courseId);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping
	public PagingDto<SwapPreview> getListSwap() {
		var list = swapService.getAll();
		var res = swapService.getPreviews(list, 1, 3);
		return new PagingDto<SwapPreview>(1, list.size() / 3 + 1, res);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{page}")
	public PagingDto<SwapPreview> getListSwap(@PathVariable(name = "page") int page) {
		var list = swapService.getAll();
		var res = swapService.getPreviews(list, page, 3);
		return new PagingDto<SwapPreview>(page, list.size() / 3 + 1, res);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/add")
	public ResponseEntity addSwap(@RequestBody SwapCreateFromView swapCreateFromView) {
		var swapCreate = new SwapCreate(0, 0, new ArrayList<Integer>());
		var list = new ArrayList<Integer>();
		swapCreate.setCourseId(_cs.getByCodeAndPracticeAndStudy(swapCreateFromView.getCourseCode(),
				swapCreateFromView.getGroupSwap().getPracticeGroup(),
				swapCreateFromView.getGroupSwap().getPracticeGroup())
				.get(0).getId());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		String userName = authentication.getName();
		UserPreview userPreview = _us.getUserPreviewByEmail(userName);
		
		swapCreate.setUserId(userPreview.getId());
		swapService.create(swapCreate);
		return ResponseEntity.ok().body(swapCreate);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteSwap(@PathVariable(name = "id") Integer swapId) {
		int res = swapService.delete(swapId);
		return ResponseEntity.ok().build();
	}
}
