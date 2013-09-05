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
import com.flash.jewelry.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private ProductMapper productMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteProduct() throws Exception {
		int id = 1;
		productMapper.deleteProduct(id);
		Product goldType = productMapper.selectProductById(id);
		assertNull(goldType);
	}

	@Test
	public void insertProduct() {
		Product goldType = new Product();
		goldType.setNumber("88");
		goldType.setName("88");		
		productMapper.insertProduct(goldType);
		goldType = productMapper.selectProductByNumber("88");
		assertEquals("88", goldType.getNumber());
	}

	@Test
	public void updateProduct() {
		Product goldType = new Product();
		goldType.setNumber("88");
		goldType.setName("88");		
		productMapper.insertProduct(goldType);
		goldType.setName("99");
		productMapper.updateProduct(goldType);
		goldType = productMapper.selectProductByNumber("88");
		assertEquals("99", goldType.getName());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;
		// insert one row
		Product goldType = new Product();
		goldType.setId(1);
		goldType.setNumber("88");
		goldType.setName("88");		
		productMapper.insertProduct(goldType);

		// check repeat row count
		goldType.setId(0);
		goldType.setNumber("88");
		int actulVal = productMapper.repeatRowByNumber(goldType);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;
		// insert one row
		Product goldType = new Product();
		goldType.setId(1);
		goldType.setName("W5");
		goldType.setNumber("88");		
		productMapper.insertProduct(goldType);

		// check repeat row count
		goldType.setId(1);
		goldType.setNumber("99");		
		int actulVal = productMapper.repeatRowByNumber(goldType);
		assertEquals(expectVal, actulVal);
	}
	@Test
	public void selectProduct(){
		int expectVal = 2;		

		// insert two row data
		Product goldType = new Product();
		goldType.setId(1);
		goldType.setNumber("88");
		goldType.setName("88");		
		productMapper.insertProduct(goldType);
		
		goldType = new Product();
		goldType.setId(2);
		goldType.setNumber("99");
		goldType.setName("99");
		productMapper.insertProduct(goldType);
		
		Collection<Product> list = productMapper.selectProduct();
		//assertEquals(expectVal, list.size());
		assertTrue(list.size()>=expectVal);
		
	}
	
	@Test
	public void selectProductById(){
		String expectVal = "1";		

		// insert two row data			
		
		Product goldType2 = productMapper.selectProductById(1);
		//assertEquals(expectVal, list.size());
		assertEquals(1, goldType2.getId());
		
	}
	
	@Test
	public void selectProductByNum(){
		String expectVal = "01";		
				
		Product goldType2 = productMapper.selectProductByNumber("01");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, goldType2.getNumber());
		
	}
}
