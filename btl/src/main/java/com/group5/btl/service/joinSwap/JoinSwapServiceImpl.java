package com.group5.btl.service.joinSwap;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.JoinSwapRepository;

@Service
public class JoinSwapServiceImpl implements JoinSwapService {
    private JoinSwapRepository _joinSwapRepository;

    public JoinSwapServiceImpl(JoinSwapRepository joinSwapRepository) {
        super();
        _joinSwapRepository = joinSwapRepository;
    }

}