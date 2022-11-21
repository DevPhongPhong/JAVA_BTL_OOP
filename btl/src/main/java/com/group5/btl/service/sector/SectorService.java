package com.group5.btl.service.sector;

import com.group5.btl.dto.sector.SectorCreater;
import com.group5.btl.model.Sector;

public interface SectorService {
	Sector findByCode(String code);
	void createSector(SectorCreater sectorCreater);
}
