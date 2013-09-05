package com.flash.jewelry.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.Material;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private MaterialMapper materialMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteMaterial() throws Exception {
		int id = 1;
		materialMapper.deleteMaterial(id);
		Material material = materialMapper.selectMaterialById(id);
		assertNull(material);
	}

	@Test
	public void insertMaterial() {
		Material material = new Material();
		material.setNumber("88");
		material.setName("88");
		material.setSort(0);
		material.setWeight(new BigDecimal(0));
		materialMapper.insertMaterial(material);
		material = materialMapper.selectMaterialByNumber("88");
		assertEquals("88", material.getNumber());
	}

	@Test
	public void updateMaterial() {
		Material material = new Material();
		material.setNumber("88");
		material.setName("88");
		material.setSort(0);
		material.setWeight(new BigDecimal(0));
		materialMapper.insertMaterial(material);
		material.setName("99");
		materialMapper.updateMaterial(material);
		material = materialMapper.selectMaterialByNumber("88");
		assertEquals("99", material.getName());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;

		// insert one row
		Material material = new Material();
		material.setId(1);
		material.setNumber("88");
		material.setName("88");
		material.setWeight(new BigDecimal(0));
		material.setSort(0);
		materialMapper.insertMaterial(material);

		// check repeat row count
		material.setId(0);
		material.setNumber("88");
		int actulVal = materialMapper.repeatRowByNumber(material);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;

		// insert one row
		Material material = new Material();
		material.setId(1);
		material.setName("W5");
		material.setNumber("88");
		material.setWeight(new BigDecimal(0));
		material.setSort(0);
		materialMapper.insertMaterial(material);

		// check repeat row count
		material.setId(1);
		material.setNumber("99");		
		int actulVal = materialMapper.repeatRowByNumber(material);
		assertEquals(expectVal, actulVal);
	}
	@Test
	public void selectMaterial(){
		int expectVal = 2;		

		// insert two row data
		Material material = new Material();
		material.setId(1);
		material.setNumber("88");
		material.setName("88");
		material.setWeight(new BigDecimal(0));
		material.setSort(0);
		materialMapper.insertMaterial(material);
		
		material = new Material();
		material.setId(2);
		material.setNumber("99");
		material.setName("99");
		material.setWeight(new BigDecimal(0));
		material.setSort(0);
		materialMapper.insertMaterial(material);
		
		Collection<Material> list = materialMapper.selectMaterial();
		//assertEquals(expectVal, list.size());
		assertTrue(list.size()>=expectVal);
		
	}
	
	@Test
	public void selectMaterialById(){
		String expectVal = "1";		

		// insert two row data			
		
		Material material2 = materialMapper.selectMaterialById(1);
		//assertEquals(expectVal, list.size());
		assertEquals(1, material2.getId());
		
	}
	
	@Test
	public void selectMaterialByNum(){
		String expectVal = "W5";		
				
		Material material2 = materialMapper.selectMaterialByNumber("W5");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, material2.getNumber());
		
	}
}
