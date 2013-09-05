package com.flash.jewelry.model;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductStyle extends BaseData {	
	
	
	private Product product;	
	
	
	public ProductStyle(){
		product = new Product();
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}	
}
