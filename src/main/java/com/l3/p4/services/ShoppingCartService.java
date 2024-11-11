package com.l3.p4.services;

import com.l3.p4.entity.CartItem;
import com.l3.p4.entity.ShoppingCart;

public interface ShoppingCartService {

    // Create a new shopping cart
    ShoppingCart createCart();

    // Add an item to the cart
    CartItem addItemToCart(Long cartId, Long productId, int quantity);

    // Remove an item from the carts
    void removeItemFromCart(Long cartId, Long cartItemId);

    // Get cart details by cart ID
    ShoppingCart getCartById(Long cartId);

    // Calculate the total price of the cart
    double calculateTotalPrice(Long cartId);
}

