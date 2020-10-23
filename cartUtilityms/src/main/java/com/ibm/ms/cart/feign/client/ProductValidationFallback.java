package com.ibm.ms.cart.feign.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibm.ms.cart.model.Product;


@Component
public class ProductValidationFallback implements ProductValidationClient {

	@Override
	public ResponseEntity<Product> validateProduct(int productID) {

		System.out.println("-----------------ProductValidationFallback :xxxxxxx: validateProduct--------------");
		//return new ResponseEntity<>(new Product(), HttpStatus.OK);
		return new ResponseEntity<Product>(new Product(), HttpStatus.NOT_FOUND);
	}

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "--------------XXXXX-----------------";
	}
	
	
}
