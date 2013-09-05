package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInQueryParam;

public interface MaterialInMapper {

	void deleteMaterialIn(long id);
	MaterialIn selectMaterialInById(long id);
	void insertMaterialIn(MaterialIn materialIn);
	MaterialIn selectMaterialInByNumber(String string);
	void updateMaterialIn(MaterialIn materialIn);
	int repeatRowByNumber(MaterialIn materialIn);	
	Collection<MaterialIn> selectMaterialIn();
	Collection<MaterialIn> findMateriallIn(MaterialInQueryParam queryParam);
	void submitBill(long billId);	
}
