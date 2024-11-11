package com.l3.p4.services;

import com.l3.p4.dto.OrderDTO;


public interface OrderService {

    // Place an order for a specific cart
    OrderDTO placeOrder(Long cartId);

    // Retrieve an order by its ID
    OrderDTO getOrderById(Long orderId);

    // Update the status of an order
    OrderDTO updateOrderStatus(Long orderId, String status);
}

