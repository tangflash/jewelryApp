package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.Product;

public interface ProductMapper {
	Collection<Product> selectProduct();
	void updateProduct(Product product); 
	void deleteProduct(long id);
	void insertProduct(Product product);	
	Product selectProductByNumber(String number);
	Product selectProductByNumberOrName(String numberOrName);
	Product selectProductById(long id);
	int repeatRowByNumber(Product product);	
	int repeatRowByName(Product product);
}
