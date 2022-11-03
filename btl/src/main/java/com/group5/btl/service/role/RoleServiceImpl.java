package com.group5.btl.service.role;

import java.util.List;


import org.springframework.stereotype.Service;

import com.group5.btl.model.Role;

import com.group5.btl.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository _roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super();
        _roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        var res = _roleRepository.findAll();
        return res;
    }
}
