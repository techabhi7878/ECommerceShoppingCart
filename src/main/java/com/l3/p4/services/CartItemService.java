package com.l3.p4.services;

import com.l3.p4.entity.CartItem;

public interface CartItemService {

    // Get a CartItem by its ID
    CartItem getCartItemById(Long cartItemId);
}
