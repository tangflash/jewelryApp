package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.ProductMapper;
import com.flash.jewelry.model.Product;
import com.flash.jewelry.service.ProductService;

public class ProductManager implements ProductService {
	
	@Autowired
	private ProductMapper productMapper = null;
	
	
	public ProductManager(){		
	}
	public Collection<Product> selectProduct(List list) {		
		return productMapper.selectProduct();
	}

	public void updateProduct(Product goldType) {
		productMapper.updateProduct(goldType);
	}

	public void deleteProduct(long id) {
		productMapper.deleteProduct(id);
	}

	public void insertProduct(Product goldType) {
		productMapper.insertProduct(goldType);

	}

	public boolean isRepeatByNum(Product product) {
		//goldType goldType = ProductMapper.selectgoldTypeByNumber(number);
		int rowCount = productMapper.repeatRowByNumber(product);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public Product selectProductById(long id) {
		return productMapper.selectProductById(id);
	}
	public Product selectProductByNum(String num) {		
		return productMapper.selectProductByNumber(num);
	}
	public Product selectProductByNumberOrName(String numberOrName) {
		return productMapper.selectProductByNumberOrName(numberOrName);
	}
	public boolean isRepeatByName(Product product) {		
		int rowCount = productMapper.repeatRowByName(product);
		if (rowCount > 0)
			return true;
		else
			return false;
	}	

}
