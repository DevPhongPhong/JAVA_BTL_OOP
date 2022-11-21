package com.group5.btl.service;

import java.util.List;

import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.model.SwapWish;

public interface SwapWishService {
    SwapWish GetSwapWishByID(int id);
    SwapWishPreview GetSwapWishPreview(SwapWish sw);
    void deleteSwapWishById(Integer id);
	List<UserPreview> getUserPreviewsJoinSwapWish(Integer swapId);
}
