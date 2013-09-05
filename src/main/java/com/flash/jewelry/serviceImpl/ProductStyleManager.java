package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.ProductStyleMapper;
import com.flash.jewelry.dao.ProductStyleMapper;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.ProductStyleService;

public class ProductStyleManager implements ProductStyleService {
	
	@Autowired
	private ProductStyleMapper productStyleMapper = null;
	
	
	public ProductStyleManager(){		
	}
	public Collection<ProductStyle> selectProductStyle(List list) {		
		return productStyleMapper.selectProductStyle();
	}

	public void updateProductStyle(ProductStyle goldType) {
		productStyleMapper.updateProductStyle(goldType);
	}

	public void deleteProductStyle(long id) {
		productStyleMapper.deleteProductStyle(id);
	}

	public void insertProductStyle(ProductStyle goldType) {
		productStyleMapper.insertProductStyle(goldType);

	}

	public boolean isRepeatByNum(ProductStyle goldType) {
		//goldType goldType = ProductStyleMapper.selectgoldTypeByNumber(number);
		int rowCount = productStyleMapper.repeatRowByNumber(goldType);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public ProductStyle selectProductStyleById(long id) {
		return productStyleMapper.selectProductStyleById(id);
	}
	public ProductStyle selectProductStyleByNum(String num) {		
		return productStyleMapper.selectProductStyleByNumber(num);
	}
	
	public boolean isRepeatByName(ProductStyle productStyle) {
		int rowCount = productStyleMapper.repeatRowByName(productStyle);
		if (rowCount > 0)
			return true;
		else
			return false;
	}
	public ProductStyle getStyleByNumOrName(String styleName) {		
		return productStyleMapper.getStyleByNumOrName(styleName);
	}	

}
