package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.ProductStyle;

public interface ProductStyleService {
	Collection<ProductStyle> selectProductStyle(List list);
	ProductStyle selectProductStyleById(long id);
	void updateProductStyle(ProductStyle productStyle); 
	void deleteProductStyle(long id);
	void insertProductStyle(ProductStyle productStyle);
	boolean isRepeatByNum(ProductStyle productStyle);
	boolean isRepeatByName(ProductStyle productStyle);
	ProductStyle selectProductStyleByNum(String num);
	ProductStyle getStyleByNumOrName(String styleName);
}
