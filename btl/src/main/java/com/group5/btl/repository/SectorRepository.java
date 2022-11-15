package com.group5.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Integer> {
    Sector findBySectorCode(String sectorCode);
}
