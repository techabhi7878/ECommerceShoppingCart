package com.l3.p4.controller;

import com.l3.p4.entity.CartItem;
import com.l3.p4.entity.ShoppingCart;
import com.l3.p4.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // Create a new shopping cart
    @PostMapping("/")
    public ResponseEntity<ShoppingCart> createCart() {
        ShoppingCart cart = shoppingCartService.createCart();
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    // Add an item to the cart
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long cartId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity) {
        CartItem cartItem = shoppingCartService.addItemToCart(cartId, productId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    // Remove an item from the cart
    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        shoppingCartService.removeItemFromCart(cartId, cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get cart details by cart ID
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long cartId) {
        ShoppingCart cart = shoppingCartService.getCartById(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Calculate the total price of the cart
    @GetMapping("/{cartId}/total-price")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable Long cartId) {
        double totalPrice = shoppingCartService.calculateTotalPrice(cartId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
