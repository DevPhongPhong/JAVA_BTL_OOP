package com.group5.btl.controller.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.model.Swap;
import com.group5.btl.repository.SwapRepository;
import com.group5.btl.service.SwapService;

import java.util.*;

@RestController
@RequestMapping("/swap")
public class SwapController {
	@Autowired
	private SwapService swapService; 

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping
	public List<SwapPreview> getListSwap() {
		return swapService.getPreviews(swapService.getAll(), 1, 3);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/add")
	public ResponseEntity addSwap(@RequestBody SwapCreate swapCreate) {
		swapService.create(swapCreate);
		return ResponseEntity.ok().body(swapCreate);
	}
}
