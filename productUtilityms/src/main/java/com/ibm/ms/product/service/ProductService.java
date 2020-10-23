package com.ibm.ms.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ms.product.dao.ProductDAO;
import com.ibm.ms.product.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO productDAO;

	public List<Product> getAllProducts() {
		return productDAO.findAll();
	}
	
	public Product getProduct(int productId) {
		Optional<Product> products = productDAO.findById(productId);
		Product product  = null;
		if (products != null && products.isPresent() ){
			product = products.get();
		}
		return product;
	}

}
