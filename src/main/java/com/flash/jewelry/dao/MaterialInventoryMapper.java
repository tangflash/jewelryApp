package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.ComQueryParam;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.model.MaterialOut;

public interface MaterialInventoryMapper {
	void updateMaterIn(MaterialInventory materialInventory);
	void updateMaterOut(MaterialInventory materialInventory);
	MaterialInventory selectMaterialInventoryByMaterId(long materId);
	void insertMaterialInventory(MaterialInventory materialInventory);
	Collection<MaterialInventory> findMaterialInventory(ComQueryParam comQueryparam);
	void insertMaterialFromMaterialIn(long billId);
	void insertMaterialFromMaterialOut(long billId);
	void submitMaterialIn(long billId);
	void submitMaterialOut(long billId);
	void updateBackForMaterialOut(MaterialOut materialOut);
	void updateBackMaterialToInventory(long billId);
}
