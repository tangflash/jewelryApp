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
import com.flash.jewelry.model.ProductStyle;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductStyleMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private ProductStyleMapper productStyleMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteProductStyle() throws Exception {
		int id = 1;
		productStyleMapper.deleteProductStyle(id);
		ProductStyle productStyle = productStyleMapper.selectProductStyleById(id);
		assertNull(productStyle);
	}

	@Test
	public void insertProductStyle() {
		ProductStyle productStyle = new ProductStyle();
		productStyle.getProduct().setId(1);
		productStyle.setNumber("88");
		productStyle.setName("88");		
		productStyleMapper.insertProductStyle(productStyle);
		productStyle = productStyleMapper.selectProductStyleByNumber("88");
		assertEquals("88", productStyle.getNumber());
	}

	@Test
	public void updateProductStyle() {
		ProductStyle productStyle = new ProductStyle();
		productStyle.getProduct().setId(1);
		productStyle.setNumber("88");
		productStyle.setName("88");		
		productStyleMapper.insertProductStyle(productStyle);
		productStyle.setName("99");
		productStyleMapper.updateProductStyle(productStyle);
		productStyle = productStyleMapper.selectProductStyleByNumber("88");
		assertEquals("99", productStyle.getName());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;
		// insert one row
		ProductStyle productStyle = new ProductStyle();
		productStyle.setId(1);
		productStyle.getProduct().setId(1);
		productStyle.setNumber("88");
		productStyle.setName("88");		
		productStyleMapper.insertProductStyle(productStyle);

		// check repeat row count
		productStyle.setId(0);
		productStyle.setNumber("88");
		int actulVal = productStyleMapper.repeatRowByNumber(productStyle);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;
		// insert one row
		ProductStyle productStyle = new ProductStyle();
		productStyle.setId(1);
		productStyle.getProduct().setId(1);
		productStyle.setName("W5");
		productStyle.setNumber("88");		
		productStyleMapper.insertProductStyle(productStyle);

		// check repeat row count
		productStyle.setId(1);
		productStyle.setNumber("99");		
		int actulVal = productStyleMapper.repeatRowByNumber(productStyle);
		assertEquals(expectVal, actulVal);
	}
	@Test
	public void selectProductStyle(){
		int expectVal = 2;		

		// insert two row data
		ProductStyle productStyle = new ProductStyle();
		productStyle.setId(1);
		productStyle.getProduct().setId(1);
		productStyle.setNumber("88");
		productStyle.setName("88");		
		productStyleMapper.insertProductStyle(productStyle);
		
		productStyle = new ProductStyle();
		productStyle.getProduct().setId(1);
		productStyle.setId(2);
		productStyle.setNumber("99");
		productStyle.setName("99");
		productStyleMapper.insertProductStyle(productStyle);
		
		Collection<ProductStyle> list = productStyleMapper.selectProductStyle();
		//assertEquals(expectVal, list.size());
		assertTrue(list.size()>=expectVal);
		
	}
	
	@Test
	public void selectProductStyleById(){
		String expectVal = "1";		

		// insert two row data			
		
		ProductStyle productStyle2 = productStyleMapper.selectProductStyleById(1);
		//assertEquals(expectVal, list.size());
		assertEquals(1, productStyle2.getId());
		
	}
	
	@Test
	public void selectProductStyleByNum(){
		String expectVal = "AB0474";		
				
		ProductStyle productStyle2 = productStyleMapper.selectProductStyleByNumber("AB0474");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, productStyle2.getNumber());
		
	}
	
	@Test
	public void getProductStyleByNumOrName(){
		String expectVal = "AB0474";		
				
		ProductStyle productStyle2 = productStyleMapper.selectProductStyleByNumber("AB0474");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, productStyle2.getNumber());		
		
	}
}
