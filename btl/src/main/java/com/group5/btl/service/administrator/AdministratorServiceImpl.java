package com.group5.btl.service.administrator;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private AdministratorRepository _administratorRepository;

    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        super();
        _administratorRepository = administratorRepository;
    }

}
