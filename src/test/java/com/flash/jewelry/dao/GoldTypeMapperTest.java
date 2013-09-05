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
import com.flash.jewelry.model.GoldType;

@RunWith(SpringJUnit4ClassRunner.class)
public class GoldTypeMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private GoldTypeMapper goldTypeMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteGoldType() throws Exception {
		int id = 1;
		goldTypeMapper.deleteGoldType(id);
		GoldType goldType = goldTypeMapper.selectGoldTypeById(id);
		assertNull(goldType);
	}

	@Test
	public void insertGoldType() {
		GoldType goldType = new GoldType();
		goldType.setNumber("88");
		goldType.setName("88");		
		goldTypeMapper.insertGoldType(goldType);
		goldType = goldTypeMapper.selectGoldTypeByNumber("88");
		assertEquals("88", goldType.getNumber());
	}

	@Test
	public void updateGoldType() {
		GoldType goldType = new GoldType();
		goldType.setNumber("88");
		goldType.setName("88");		
		goldTypeMapper.insertGoldType(goldType);
		goldType.setName("99");
		goldTypeMapper.updateGoldType(goldType);
		goldType = goldTypeMapper.selectGoldTypeByNumber("88");
		assertEquals("99", goldType.getName());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;
		// insert one row
		GoldType goldType = new GoldType();
		goldType.setId(1);
		goldType.setNumber("88");
		goldType.setName("88");		
		goldTypeMapper.insertGoldType(goldType);

		// check repeat row count
		goldType.setId(0);
		goldType.setNumber("88");
		int actulVal = goldTypeMapper.repeatRowByNumber(goldType);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;
		// insert one row
		GoldType goldType = new GoldType();
		goldType.setId(1);
		goldType.setName("W5");
		goldType.setNumber("88");		
		goldTypeMapper.insertGoldType(goldType);

		// check repeat row count
		goldType.setId(1);
		goldType.setNumber("99");		
		int actulVal = goldTypeMapper.repeatRowByNumber(goldType);
		assertEquals(expectVal, actulVal);
	}
	@Test
	public void selectGoldType(){
		int expectVal = 2;		

		// insert two row data
		GoldType goldType = new GoldType();
		goldType.setId(1);
		goldType.setNumber("88");
		goldType.setName("88");		
		goldTypeMapper.insertGoldType(goldType);
		
		goldType = new GoldType();
		goldType.setId(2);
		goldType.setNumber("99");
		goldType.setName("99");
		goldTypeMapper.insertGoldType(goldType);
		
		Collection<GoldType> list = goldTypeMapper.selectGoldType();
		//assertEquals(expectVal, list.size());
		assertTrue(list.size()>=expectVal);
		
	}
	
	@Test
	public void selectGoldTypeById(){
		String expectVal = "1";		

		// insert two row data			
		
		GoldType goldType2 = goldTypeMapper.selectGoldTypeById(1);
		//assertEquals(expectVal, list.size());
		assertEquals(1, goldType2.getId());
		
	}
	
	@Test
	public void selectGoldTypeByNum(){
		String expectVal = "18k";		
				
		GoldType goldType2 = goldTypeMapper.selectGoldTypeByNumber("18k");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, goldType2.getNumber());
		
	}
	
	@Test
	public void getGoldTypeByNumOrName(){
		//String expectVal = "18k";		
				
		GoldType goldType2 = goldTypeMapper.getGoldTypeByNumOrName("18k");
		//assertEquals(expectVal, list.size());
		assertEquals(1, goldType2.getId());
		
		goldType2 = goldTypeMapper.getGoldTypeByNumOrName("18K°×");
		//assertEquals(expectVal, list.size());
		assertEquals(1, goldType2.getId());
		
	}
}
