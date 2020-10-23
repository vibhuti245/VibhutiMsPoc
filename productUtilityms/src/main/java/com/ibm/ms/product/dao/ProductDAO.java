package com.ibm.ms.product.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ibm.ms.product.model.Product;


@Component
public interface ProductDAO extends JpaRepository<Product, Integer> {

}
