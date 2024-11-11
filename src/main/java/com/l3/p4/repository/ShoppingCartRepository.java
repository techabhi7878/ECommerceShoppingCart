package com.l3.p4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.l3.p4.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
