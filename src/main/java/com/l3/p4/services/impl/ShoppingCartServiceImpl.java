package com.l3.p4.services.impl;

import com.l3.p4.entity.CartItem;
import com.l3.p4.entity.Product;
import com.l3.p4.entity.ShoppingCart;
import com.l3.p4.exception.InsufficientStockException;
import com.l3.p4.exception.ProductNotFoundException;
import com.l3.p4.repository.CartItemRepository;
import com.l3.p4.repository.ProductRepository;
import com.l3.p4.repository.ShoppingCartRepository;
import com.l3.p4.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart createCart() {
        ShoppingCart cart = new ShoppingCart();
        return shoppingCartRepository.save(cart);
    }

    @Override
    public CartItem addItemToCart(Long cartId, Long productId, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found with ID " + cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        if (quantity > product.getStockQuantity()) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long cartItemId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found with ID " + cartId));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ProductNotFoundException("CartItem with ID " + cartItemId + " not found"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public ShoppingCart getCartById(Long cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found with ID " + cartId));
    }

    @Override
    public double calculateTotalPrice(Long cartId) {
        ShoppingCart cart = getCartById(cartId);
        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
