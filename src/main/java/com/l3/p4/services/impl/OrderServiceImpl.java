package com.l3.p4.services.impl;

import com.l3.p4.dto.OrderDTO;
import com.l3.p4.entity.Order;
import com.l3.p4.entity.ShoppingCart;
import com.l3.p4.exception.CartEmptyException;
import com.l3.p4.exception.ProductNotFoundException;
import com.l3.p4.repository.OrderRepository;
import com.l3.p4.repository.ShoppingCartRepository;
import com.l3.p4.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDTO placeOrder(Long cartId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found with ID " + cartId));

        if (cart.getCartItems().isEmpty()) {
            throw new CartEmptyException("Cannot place an order with an empty cart");
        }

        Order order = new Order();
        order.setCart(cart);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ProductNotFoundException("Order with ID " + orderId + " not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ProductNotFoundException("Order with ID " + orderId + " not found"));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }
}
