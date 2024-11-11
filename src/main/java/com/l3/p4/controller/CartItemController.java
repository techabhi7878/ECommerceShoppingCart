package com.l3.p4.controller;

import com.l3.p4.entity.CartItem;
import com.l3.p4.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // Get a cart item by its ID
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
}
