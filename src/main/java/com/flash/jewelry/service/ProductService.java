package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.Product;

public interface ProductService {
	Collection<Product> selectProduct(List list);
	Product selectProductById(long id);
	void updateProduct(Product product); 
	void deleteProduct(long id);
	void insertProduct(Product product);
	boolean isRepeatByNum(Product product);
	boolean isRepeatByName(Product product);
	Product selectProductByNum(String num);
	Product selectProductByNumberOrName(String numberOrName);
}
