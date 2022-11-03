package com.group5.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.btl.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
//    Role findByName(String name);
}
