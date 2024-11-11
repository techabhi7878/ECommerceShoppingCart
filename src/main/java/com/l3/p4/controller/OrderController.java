package com.l3.p4.controller;

import com.l3.p4.dto.OrderDTO;
import com.l3.p4.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place an order for a specific cart
    @PostMapping("/place/{cartId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long cartId) {
        OrderDTO placedOrder = orderService.placeOrder(cartId);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    // Get an order by its ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Update the status of an order
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
