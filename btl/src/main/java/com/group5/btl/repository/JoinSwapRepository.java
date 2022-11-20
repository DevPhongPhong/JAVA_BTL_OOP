package com.group5.btl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;

@Repository
public interface JoinSwapRepository extends JpaRepository<JoinSwap,Integer>{
    List<JoinSwap> findByUserId(Student userId);
}
