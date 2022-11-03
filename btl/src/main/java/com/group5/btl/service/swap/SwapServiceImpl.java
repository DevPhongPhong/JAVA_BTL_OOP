package com.group5.btl.service.swap;
import org.springframework.stereotype.Service;

import com.group5.btl.repository.SwapRepository;

@Service
public class SwapServiceImpl implements SwapService {
    private SwapRepository _swapRepository;

    public SwapServiceImpl(SwapRepository swapRepository) {
        super();
        _swapRepository = swapRepository;
    }

}