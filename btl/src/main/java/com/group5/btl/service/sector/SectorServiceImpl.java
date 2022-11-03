package com.group5.btl.service.sector;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService {
    private SectorRepository _sectorRepository;

    public SectorServiceImpl(SectorRepository sectorRepository) {
        super();
        _sectorRepository = sectorRepository;
    }

}