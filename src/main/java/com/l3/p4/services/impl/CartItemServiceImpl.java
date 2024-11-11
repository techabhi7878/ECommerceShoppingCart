package com.l3.p4.services.impl;

import com.l3.p4.entity.CartItem;
import com.l3.p4.exception.ProductNotFoundException;
import com.l3.p4.repository.CartItemRepository;
import com.l3.p4.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ProductNotFoundException("CartItem with ID " + cartItemId + " not found"));
    }
}


