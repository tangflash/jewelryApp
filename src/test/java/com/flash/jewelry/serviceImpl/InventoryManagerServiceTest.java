package com.flash.jewelry.serviceImpl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.service.InventoryManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryManagerServiceTest extends AbstractContextControllerTests {
	@Autowired
	private InventoryManagerService inventoryManagerService;
	
	@Test
	public void addMaterialIn() {
		long materId = 1;
		int amount = 1;
		inventoryManagerService.addMaterialIn(materId, amount);
		MaterialInventory materialInventory = inventoryManagerService.getMaterialInventory(materId);
		assertEquals(amount,materialInventory.getInAmount());
	}
	
	@Test
	public void addMaterialOut() {		
		long materId = 1;
		int amount = 1;
		inventoryManagerService.addMaterialOut(materId, amount);
		MaterialInventory materialInventory = inventoryManagerService.getMaterialInventory(materId);
		assertEquals(amount,materialInventory.getOutAmount());
	}	
	
}
