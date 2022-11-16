package com.group5.btl.service;

import com.group5.btl.dto.swap.JoinSwapPreview;
import com.group5.btl.model.JoinSwap;

public interface JoinSwapService {
    JoinSwap GetByID(int id);
    JoinSwapPreview GetPreview(JoinSwap js);
}
