package com.group5.btl.controller.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.swap.SwapInfo;
import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.model.Swap;
import com.group5.btl.service.SwapService;

import java.util.*;

@RestController
@RequestMapping("/swapwish")
public class SwapWishController {
	
	@Autowired
	private SwapService swapService;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{id}")
	public List<SwapWishPreview> getListSwapWish(@PathVariable(name = "id") Integer swapId) {
		Swap swap = swapService.getById(swapId);
		SwapInfo swapInfo = swapService.getInfo(swap);
		return swapInfo.getListSwapWishPreview();
	}
}
