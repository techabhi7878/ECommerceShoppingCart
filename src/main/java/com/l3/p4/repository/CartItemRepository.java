package com.l3.p4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l3.p4.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
