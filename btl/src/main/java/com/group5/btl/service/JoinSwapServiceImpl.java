package com.group5.btl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.swap.JoinSwapPreview;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.repository.JoinSwapRepository;

@Service
public class JoinSwapServiceImpl implements JoinSwapService {
    @Autowired
    private JoinSwapRepository _jsr;

    @Override
    public JoinSwap GetByID(int id) {
        return _jsr.findById(id).get();
    }

    @Override
    public JoinSwapPreview GetPreview(JoinSwap js) {
        return new JoinSwapPreview(js.getId(),js.getUserId().getId(), js.getUserId().getName());
    }
    
}
