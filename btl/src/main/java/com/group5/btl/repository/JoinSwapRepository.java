package com.group5.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.JoinSwap;

@Repository
public interface JoinSwapRepository extends JpaRepository<JoinSwap,Integer>{
    
}
