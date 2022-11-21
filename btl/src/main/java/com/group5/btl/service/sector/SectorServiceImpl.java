package com.group5.btl.service.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.model.Sector;
import com.group5.btl.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService{

	@Autowired
	private SectorRepository sectorRepository;
	
	@Override
	public Sector findByCode(String code) {
		Sector sector = sectorRepository.findBySectorCode(code);
		return sector;
	}
	
}
