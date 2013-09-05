package com.flash.jewelry.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialInventoryMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private MaterialInventoryMapper materialInventoryMapper;
	@Autowired
	private MaterialOutDetailMapper materialOutDetailMapper;
	

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralOutfOutputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void selectMaterialInventoryByMaterId() throws Exception {		
		int id = 1;
		MaterialInventory materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(id);		
		assertEquals(id, materialInventory.getId());
	}
	
	@Test
	public void insertMaterialInventory() throws Exception {		
		MaterialInventory materialInventory = new MaterialInventory();
		materialInventory.setMaterId(2);
		materialInventory.setInAmount(0);
		materialInventory.setInWeight(new BigDecimal(0));
		materialInventory.setOutAmount(0);
		materialInventory.setOutWeight(new BigDecimal(0));
		materialInventoryMapper.insertMaterialInventory(materialInventory);		
		assertEquals(2, materialInventory.getMaterId());
	}
	
	@Test
	public void updateMaterIn() throws Exception {	
		MaterialInventory materialInventory = new MaterialInventory();  
		materialInventory.setMaterId(1);
		materialInventory.setInAmount(1);
		materialInventoryMapper.updateMaterIn(materialInventory);	
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);		
		assertEquals(1, materialInventory.getInAmount());
	}
	
	@Test
	public void updateMaterOut() throws Exception {		
		MaterialInventory materialInventory = new MaterialInventory();  
		materialInventory.setMaterId(1);
		materialInventory.setOutAmount(1);		
		materialInventoryMapper.updateMaterOut(materialInventory);	
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);				
		assertEquals(1, materialInventory.getOutAmount());
	}	
	
	@Test
	public void submitMaterialIn() throws Exception {	
		MaterialInventory materialInventory = new MaterialInventory();		
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);
		int inAmount = materialInventory.getInAmount();
		materialInventoryMapper.insertMaterialFromMaterialIn(1);
		materialInventoryMapper.submitMaterialIn(1);			
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);				
		assertEquals(inAmount + 11, materialInventory.getInAmount());
	}
	@Test
	@Rollback(false)
	public void submitMaterialOut() throws Exception {	
		MaterialInventory materialInventory = new MaterialInventory();		
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);
		int outAmount = materialInventory.getOutAmount();
		materialInventoryMapper.insertMaterialFromMaterialOut(1);
		materialInventoryMapper.submitMaterialOut(1);			
		materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);				
		assertEquals(outAmount + 11, materialInventory.getOutAmount());
	}
	
	@Test
	public void updateBackForMaterialOut() throws Exception {
		MaterialOut materialOut = new MaterialOut();
		materialOut.setId(1);
		materialOut.setClientId(1);
		materialInventoryMapper.updateBackForMaterialOut(materialOut);
		MaterialOutDetail materialOutDetail  = materialOutDetailMapper.selectMaterialOutDetailById(1);
		assertEquals(1, materialOutDetail.getBackAmount());
	}
	
	@Test	
	public void updateBackMaterialToInventory() throws Exception {
		long billId = 1;
		materialInventoryMapper.updateBackMaterialToInventory(billId);
		MaterialInventory materialInventory = materialInventoryMapper.selectMaterialInventoryByMaterId(1);		
		assertEquals(0, materialInventory.getInAmount() - materialInventory.getOutAmount());
		assertEquals(0.0000, materialInventory.getInWeight().subtract(materialInventory.getOutWeight()));
	}
}
