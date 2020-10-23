package com.ibm.ms.cart.feign.client;

import org.springframework.stereotype.Component;


@Component
public class TokenAuthorizationFallback implements TokenAuthorizationClient {

	@Override
	public boolean validateToken(String token) {
		System.out.println("-----------------TokenAuthorizationFallback :: vlidateToken--------------");
		return false;
	}
}
