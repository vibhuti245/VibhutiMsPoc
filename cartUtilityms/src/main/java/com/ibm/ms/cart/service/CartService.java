package com.ibm.ms.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.ms.cart.dao.CartDAO;
import com.ibm.ms.cart.feign.client.ProductValidationClient;
import com.ibm.ms.cart.model.Cart;
import com.ibm.ms.cart.model.Product;

@Service
public class CartService {

	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private ProductValidationClient productvalidatorClient;
	@Autowired
	private LoadBalancerClient lbClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	public List<Cart> getAllCarts() {
		return cartDAO.findAll();
	}

	public Cart getCart(int cartId) {
		Optional<Cart> carts = cartDAO.findById(cartId);
		Cart cart = null;
		if (carts != null) {
			cart = carts.get();
		}
		return cart;
	}

	public String addProduct(int productId) {

		ResponseEntity<Product> response = productvalidatorClient.validateProduct(productId);
		if(HttpStatus.NOT_FOUND.equals(response.getStatusCode() )){
			return "Product #"+ productId + " not valid !!";
		}
		Product product = response.getBody();
		System.out.println(product);
		boolean exists = cartDAO.existsById(productId);

		if (!exists) {
			cartDAO.save(new Cart(productId, 1, product.getProductDesc(), product.getProductPrice()));
		} else {
			Cart cart = getCart(productId);
			cartDAO.save(new Cart(productId, cart.getQuantity() + 1, product.getProductDesc(),
					cart.getPrice() + product.getProductPrice()));
		}
		return "Product id " + productId + " added successfully.";
	}

	public String deleteProduct(int productId) {
		// Product product = productvalidatorClient.validateProduct(productId);
		ResponseEntity<Product> response = productvalidatorClient.validateProduct(productId);
		Product product = response.getBody();

		Cart cart = getCart(productId);
		if (cart == null) {

		} else if (cart.getQuantity() > 1) {
			cartDAO.save(new Cart(productId, cart.getQuantity() - 1, product.getProductDesc(),
					cart.getPrice() - product.getProductPrice()));
		} else {
			cartDAO.deleteById(productId);
		}

		return "Product id " + productId + " deleted successfully.";
	}

	public String clearCart() {
		cartDAO.deleteAll();
		return "Cart cleared successfully.";
	}

	/*
	 * public String checkoutCart() { cartDAO.deleteAll(); return
	 * "Cart checkout successfully."; }
	 */
	public String test() {

		ServiceInstance instance = lbClient.choose("productms");
		//String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/default";
		List<ServiceInstance> instances = discoveryClient.getInstances("productms");
		System.out.println(instances);
		for (ServiceInstance inst : instances) {
			System.out.println(inst.getHost() + ":" + inst.getPort());
		}
		
		instances = discoveryClient.getInstances("accountms");
		for (ServiceInstance inst : instances) {
			System.out.println(inst.getHost() + ":" + inst.getPort());
		}
		System.out.println(productvalidatorClient);
		System.out.println(productvalidatorClient.test());
		System.out.println(productvalidatorClient.validateProduct(6));
		String url = "http://"+instances.get(0).getHost() + ":" + instances.get(0).getPort()+"/default";
		System.out.println(instance + "  ::: URL is  " + url);
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> discountEntityResponse = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		String response = discountEntityResponse.getBody();
		return response; 
		//return productvalidatorClient.test();
	}
}
