package com.ibm.ms.cart.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ibm.ms.cart.model.Cart;


@Component
public interface CartDAO extends JpaRepository<Cart, Integer> {

}
