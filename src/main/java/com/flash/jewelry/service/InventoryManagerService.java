package com.flash.jewelry.service;

import java.util.Collection;

import com.flash.jewelry.model.ComQueryParam;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.model.MaterialOut;

public interface InventoryManagerService {
	void addMaterialIn(long materId,int amount);
	void addMaterialOut(long materId,int amount);
	MaterialInventory getMaterialInventory(long materId);
	Collection<MaterialInventory> findMaterialInventory(ComQueryParam comQueryParam);
	void submitMaterialIn(long billId);
	void submitMaterialOut(MaterialOut materialOut);
	void balanceInventory(long clientId);
}
