package com.group5.btl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Swap;
import com.group5.btl.model.SwapWish;

@Repository
public interface SwapWishRepository extends JpaRepository<SwapWish,Integer>{
    void deleteById(Integer id);
    Optional<SwapWish> findById(Integer id);
}
