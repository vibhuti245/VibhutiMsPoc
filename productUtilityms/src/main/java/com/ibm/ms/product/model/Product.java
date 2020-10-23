package com.ibm.ms.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {
	
		@Id
		private int productCode;
		private String productDesc;
		private double productPrice;
		
		public Product() {
		}
		
		public Product(int productCode, String productDesc, double productPrice) {
			super();
			this.productCode = productCode;
			this.productDesc = productDesc;
			this.productPrice = productPrice;
		}
		
		public int getProductCode() {
			return productCode;
		}
		public void setProductCode(int productCode) {
			this.productCode = productCode;
		}
		public String getProductDesc() {
			return productDesc;
		}
		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}
		public double getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(double productPrice) {
			this.productPrice = productPrice;
		}
		

}
