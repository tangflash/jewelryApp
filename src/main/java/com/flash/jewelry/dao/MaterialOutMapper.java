package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;

public interface MaterialOutMapper {

	void deleteMaterialOut(long id);
	MaterialOut selectMaterialOutById(long id);
	void insertMaterialOut(MaterialOut MaterialOut);
	MaterialOut selectMaterialOutByNumber(String string);
	void updateMaterialOut(MaterialOut MaterialOut);
	int repeatRowByNumber(MaterialOut MaterialOut);
	Collection<MaterialOut> selectMaterialOut();	
	void submitBill(long billId);
}
