package com.group5.btl.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Swap;

@Repository
public interface SwapRepository extends JpaRepository<Swap, Integer> {
    List<Swap> findAll();
}
