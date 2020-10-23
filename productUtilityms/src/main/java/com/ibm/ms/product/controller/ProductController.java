package com.ibm.ms.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ms.product.model.Product;
import com.ibm.ms.product.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping(path = "/default")
	public String defaultMessage() {
		return "Welcome to Product service";
	}

	@GetMapping(path = "/getAllProducts", produces = "application/json")
	public ResponseEntity<List<Product>> getAllProducts() {
		System.out.println("-------  ProductController :: getAllProducts --------------");
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/getProduct/{productId}", produces = "application/json")
	public ResponseEntity<Product> getProduct(@PathVariable int productId) {
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/validateProduct/{productId}", produces = "application/json")
	public ResponseEntity<Product> validateProduct(@PathVariable int productId) {
		Product product = productService.getProduct(productId);
		return new ResponseEntity<Product>(product, product == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	

}
