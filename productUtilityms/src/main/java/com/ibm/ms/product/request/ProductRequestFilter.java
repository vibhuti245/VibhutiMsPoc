package com.ibm.ms.product.request;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibm.ms.product.service.AuthService;

@Component
public class ProductRequestFilter extends OncePerRequestFilter {

	@Autowired
	AuthService authService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		boolean cc = !request.getServletPath().startsWith("/get");
		System.out.println("ProductRequestFilter :: "+ request.getServletPath() + " -->  " +  cc);
		return cc;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURL().toString();
		System.out.println("Filter: URL called:" + url);
		System.out.println("httpRequest: " + httpRequest.getParameterMap().size());
		for (java.util.Map.Entry<String, String[]> e : httpRequest.getParameterMap().entrySet()) {
			System.out.println(e.getKey() + " --> " + e.getValue().toString());
		}
		if (!authService.vlidateToken(request)) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			httpResponse.getOutputStream().flush();
			httpResponse.getOutputStream().println("Invalid token");
			throw new RuntimeException("-------  invalid token --------------");
		}

		filterChain.doFilter(httpRequest, httpResponse);
	}

}
