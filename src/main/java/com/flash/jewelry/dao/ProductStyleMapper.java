package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.ProductStyle;

public interface ProductStyleMapper {
	Collection<ProductStyle> selectProductStyle();
	void updateProductStyle(ProductStyle productStyle); 
	void deleteProductStyle(long id);
	void insertProductStyle(ProductStyle productStyle);	
	ProductStyle selectProductStyleByNumber(String number);
	ProductStyle selectProductStyleById(long id);
	int repeatRowByNumber(ProductStyle productStyle);
	int repeatRowByName(ProductStyle productStyle);
	ProductStyle getStyleByNumOrName(String styleName);
}
