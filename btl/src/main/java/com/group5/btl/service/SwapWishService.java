package com.group5.btl.service;

import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.model.SwapWish;

public interface SwapWishService {
    SwapWish GetSwapWishByID(int id);
    SwapWishPreview GetSwapWishPreview(SwapWish sw);
}
