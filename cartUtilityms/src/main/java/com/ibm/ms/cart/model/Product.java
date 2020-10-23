package com.ibm.ms.cart.model;


public class Product {
	
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

		@Override
		public String toString() {
			return "Product [productCode=" + productCode + ", productDesc=" + productDesc + ", productPrice="
					+ productPrice + "]";
		}
		

}
