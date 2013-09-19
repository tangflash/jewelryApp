package com.flash.jewelry.serviceImpl;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.MaterialInventoryMapper;
import com.flash.jewelry.model.ComQueryParam;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.service.InventoryManagerService;

public class InventoryManager implements InventoryManagerService {
	
	@Autowired
	private MaterialInventoryMapper materialInventoryMapper;
	
	public void addMaterialIn(long materId, int amount) {
		if (!isMaterExist(materId))
			insertEmptyMaterInventory(materId);
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterId(materId);
		materialInventory.setInAmount(amount);
		materialInventoryMapper.updateMaterIn(materialInventory);		
	}

	public void addMaterialOut(long materId, int amount) {
		if (!isMaterExist(materId))
			insertEmptyMaterInventory(materId);
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterId(materId);
		materialInventory.setOutAmount(amount);
		materialInventoryMapper.updateMaterOut(materialInventory);
		
	}
	
	public boolean isMaterExist(long materId){
		MaterialInventory materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(materId);
		if (materialInventory != null) return true;
		else return false;
	}
	
	public void insertEmptyMaterInventory(long materId){
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterId(materId);
		materialInventory.setInAmount(0);
		materialInventory.setInWeight(new BigDecimal(0));
		materialInventory.setOutAmount(0);
		materialInventory.setOutWeight(new BigDecimal(0));
		materialInventory.setSort(0);
		materialInventoryMapper.insertMaterialInventory(materialInventory);
	}

	public MaterialInventory getMaterialInventory(long materId) {
		return materialInventoryMapper.selectMaterialInventoryByMaterId(materId);
	}

	public Collection<MaterialInventory> findMaterialInventory(ComQueryParam comQueryparam) {
		return materialInventoryMapper.findMaterialInventory(comQueryparam);
	}

	public void submitMaterialIn(long billId) {
		materialInventoryMapper.insertMaterialFromMaterialIn(billId);
		materialInventoryMapper.submitMaterialIn(billId);
		
	}

	public void submitMaterialOut(MaterialOut  materialOut) {
		materialInventoryMapper.insertMaterialFromMaterialOut(materialOut.getId());
		materialInventoryMapper.submitMaterialOut(materialOut.getId());			
		//materialInventoryMapper.updateBackForMaterialOut(materialOut);
		//materialInventoryMapper.updateBackMaterialToInventory(materialOut.getId());
	}

	public void balanceInventory(long clientId) {
		materialInventoryMapper.balanceInventory(clientId);		
	}
	
}
