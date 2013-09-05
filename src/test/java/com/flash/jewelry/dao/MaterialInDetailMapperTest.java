package com.flash.jewelry.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialInDetailMapperTest extends AbstractContextControllerTests {
	
	@Autowired
	private MaterialInDetailMapper materialInDetailMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteMaterialInDetail() throws Exception {
		int id = 1;
		materialInDetailMapper.deleteMaterialInDetail(id);
		MaterialInDetail materialIn = materialInDetailMapper.selectMaterialInDetailById(id);
		assertNull(materialIn);
	}

	@Test
	public void insertMaterialInDetail() throws ParseException {
		MaterialInDetail materialInDetail = new MaterialInDetail();
		materialInDetail.setId(88);
		materialInDetail.setBillId(1);
		materialInDetail.setMaterId(1);
		materialInDetail.setAmount(11);
		materialInDetail.setWeight(new BigDecimal(1));
		materialInDetail.setSort(0);
				
		materialInDetailMapper.insertMaterialInDetail(materialInDetail);
		//materialInDetail = materialInDetailMapper.selectMaterialInDetailByBillId(1);
		//assertEquals(88, materialInDetail.getId());
		//assertn
	}

	@Test
	public void updateMaterialInDetail() {
		long id = 1;
		MaterialInDetail materialInDetail = materialInDetailMapper.selectMaterialInDetailById(id);

		materialInDetail.setAmount(999);
		materialInDetailMapper.updateMaterialInDetail(materialInDetail);
		materialInDetail = materialInDetailMapper.selectMaterialInDetailById(id);
		assertEquals(999, materialInDetail.getAmount());
	}

	
	@Test
	public void selectMaterialInDetail() {
		int expectVal = 2;

		Collection<MaterialIn> list = materialInDetailMapper.selectMaterialInDetail();
		// assertEquals(expectVal, list.size());
		assertTrue(list.size() >= expectVal);

	}

	@Test
	public void selectMaterialInDetailById() {
		// insert two row data
		MaterialInDetail materialInDetail = materialInDetailMapper.selectMaterialInDetailById(1);
		// assertEquals(expectVal, list.size());
		assertEquals(1, materialInDetail.getId());

	}
}
