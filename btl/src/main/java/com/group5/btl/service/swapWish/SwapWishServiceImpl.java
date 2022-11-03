package com.group5.btl.service.swapWish;

import org.springframework.stereotype.Service;

import com.group5.btl.repository.SwapWishRepository;

@Service
public class SwapWishServiceImpl implements SwapWishService {
    private SwapWishRepository _swapWishRepository;

    public SwapWishServiceImpl(SwapWishRepository swapWishRepository) {
        super();
        _swapWishRepository = swapWishRepository;
    }

}