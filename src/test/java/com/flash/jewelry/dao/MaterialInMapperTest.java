package com.flash.jewelry.dao;

import static org.junit.Assert.*;

import java.awt.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInQueryParam;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialInMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private MaterialInMapper materialInMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteMaterialIn() throws Exception {
		int id = 1;
		materialInMapper.deleteMaterialIn(id);
		MaterialIn materialIn = materialInMapper.selectMaterialInById(id);
		assertNull(materialIn);
	}

	@Test
	public void insertMaterialIn() throws ParseException {
		MaterialIn materialIn = new MaterialIn();
		materialIn.setBillNumber("13050401");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		materialIn.setBizDate(format.parse("2013-05-04"));

		materialIn.setCreateTime(new Date());
		materialInMapper.insertMaterialIn(materialIn);
		materialIn = materialInMapper.selectMaterialInByNumber("13050401");
		assertEquals("13050401", materialIn.getBillNumber());
	}

	@Test
	public void updateMaterialIn() {
		long id = 1;
		MaterialIn materialIn = materialInMapper.selectMaterialInById(id);

		materialIn.setBillNumber("13061602");
		materialInMapper.updateMaterialIn(materialIn);
		materialIn = materialInMapper.selectMaterialInById(id);
		assertEquals("13061602", materialIn.getBillNumber());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;

		// insert one row Material
		MaterialIn materialIn = new MaterialIn();
		materialIn.setId(0);
		materialIn.setBillNumber("13061601");
		int actulVal = materialInMapper.repeatRowByNumber(materialIn);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;

		MaterialIn materialIn = new MaterialIn();
		materialIn.setId(1);
		materialIn.setBillNumber("13061601");

		int actulVal = materialInMapper.repeatRowByNumber(materialIn);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void selectMaterial() {
		int expectVal = 3;

		Collection<MaterialIn> list = materialInMapper.selectMaterialIn();
		// assertEquals(expectVal, list.size());
		assertTrue(list.size() >= expectVal);

	}

	@Test
	public void selectMaterialById() {
		// insert two row data
		MaterialIn materialIn = materialInMapper.selectMaterialInById(1);
		// assertEquals(expectVal, list.size());
		assertEquals(1, materialIn.getId());

	}
	
	@Test
	public void findMateriallIn() throws ParseException {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		queryParam.setBizBeginDate(simpleDateFormat.parse("2013-06-16"));
		queryParam.setBizEndDate(simpleDateFormat.parse("2013-06-16"));
		Collection<MaterialIn> materialInList = materialInMapper.findMateriallIn(queryParam);		
		//assertEquals(1, materialIn.getId());
		int size = materialInList.size();
		assertEquals(2,size);
	}
	@Test
	public void submitBill(){
		materialInMapper.submitBill(1);
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");
		Collection<MaterialIn> materialInList = materialInMapper.findMateriallIn(queryParam);
		Iterator<MaterialIn> MaterialInIter = materialInList.iterator();
		MaterialIn materialIn = MaterialInIter.next();
		assertEquals("1",materialIn.getBillStatus().getNumber());
	}
}
