package com.group5.btl.controller.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.btl.dto.sector.SectorCreater;
import com.group5.btl.service.sector.SectorService;

@RestController
@RequestMapping("/sector")
public class SectorController {
	@Autowired
	private SectorService sectorService;
	
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/add")
	public ResponseEntity addCourse(@RequestBody SectorCreater sectorCreater) {
		sectorService.createSector(sectorCreater);
		return ResponseEntity.ok().body(sectorCreater);
	}
	
}
