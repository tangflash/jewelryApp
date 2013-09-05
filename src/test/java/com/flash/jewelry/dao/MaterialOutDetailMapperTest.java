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
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialOutDetailMapperTest extends AbstractContextControllerTests {
	
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
	public void deleteMaterialOutDetail() throws Exception {
		int id = 1;
		materialOutDetailMapper.deleteMaterialOutDetail(id);
		MaterialOutDetail materialOut = materialOutDetailMapper.selectMaterialOutDetailById(id);
		assertNull(materialOut);
	}

	@Test
	public void insertMaterialOutDetail() throws ParseException {
		MaterialOutDetail materialOutDetail = new MaterialOutDetail();
		materialOutDetail.setId(88);
		materialOutDetail.setBillId(1);
		materialOutDetail.setMaterId(1);
		materialOutDetail.setAmount(11);
		materialOutDetail.setWeight(new BigDecimal(1));
		materialOutDetail.setSort(0);
		materialOutDetail.setHandSize(new BigDecimal(18.2));
		materialOutDetail.setProductNameId(1);
		materialOutDetail.setStyleId(1);
		materialOutDetail.setProductAmount(5);
		materialOutDetail.setProductWeight(new BigDecimal(18.3));
		materialOutDetail.setGoldWeight(new BigDecimal(18));
		materialOutDetail.setConsumeWeight(new BigDecimal(11.2));
		materialOutDetail.setProcessCost(new BigDecimal(11.2));
		materialOutDetail.setAddProcessCost(new BigDecimal(12.8));
		materialOutDetail.setSuperSetCost(new BigDecimal(21.2));
		materialOutDetail.setFactoryAddMoney(new BigDecimal(11));
		materialOutDetail.setSecMaterId(1);
		materialOutDetail.setAmount(1);
		materialOutDetail.setSecWeight(new BigDecimal(11));
		materialOutDetail.setSecPrice(new BigDecimal(21));	
		
				
		materialOutDetailMapper.insertMaterialOutDetail(materialOutDetail);
		//materialOutDetail = materialOutDetailMapper.selectMaterialOutDetailByBillId(1);
		//assertEquals(88, materialOutDetail.getId());
		//assertn
	}

	@Test
	public void updateMaterialOutDetail() {
		long id = 1;
		MaterialOutDetail materialOutDetail = materialOutDetailMapper.selectMaterialOutDetailById(id);

		materialOutDetail.setAmount(999);
		materialOutDetailMapper.updateMaterialOutDetail(materialOutDetail);
		materialOutDetail = materialOutDetailMapper.selectMaterialOutDetailById(id);
		assertEquals(999, materialOutDetail.getAmount());
	}

	
	@Test
	public void selectMaterialOutDetail() {
		int expectVal = 2;

		Collection<MaterialOut> list = materialOutDetailMapper.selectMaterialOutDetail();
		// assertEquals(expectVal, list.size());
		assertTrue(list.size() >= expectVal);

	}

	@Test
	public void selectMaterialOutDetailById() {
		// insert two row data
		MaterialOutDetail materialOutDetail = materialOutDetailMapper.selectMaterialOutDetailById(1);
		// assertEquals(expectVal, list.size());
		assertEquals(1, materialOutDetail.getId());
		//assertEquals(1, materialOutDetail.getId());

	}
	
	@Test
	public void findMateriallOut() {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");
		// insert two row data
		Collection<MaterialOutDetail> materialOutDetailList = materialOutDetailMapper.findMateriallOut(queryParam);
		// assertEquals(expectVal, list.size());
		assertEquals(true, materialOutDetailList.size() > 0);
		//assertEquals(1, materialOutDetail.getId());

	}	
	
	@Test
	public void staticOutBillByProduct() {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");		
		Collection<MaterialOutDetail> materialOutDetailList = materialOutDetailMapper.staticOutBillByProduct(queryParam);		
		assertEquals(true, materialOutDetailList.size() > 0);
	}
	@Test
	public void staticOutBillByMainMaterial() {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");		
		Collection<MaterialOutDetail> materialOutDetailList = materialOutDetailMapper.staticOutBillByMainMaterial(queryParam);		
		assertEquals(true, materialOutDetailList.size() > 0);
	}	
	
	@Test
	public void staticOutBillBySecMaterial() {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");		
		Collection<MaterialOutDetail> materialOutDetailList = materialOutDetailMapper.staticOutBillBySecMaterial(queryParam);		
		assertEquals(true, materialOutDetailList.size() > 0);
	}
	
	@Test
	public void staticOutBillByTotalFee() {
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");		
		Collection<MaterialOutDetail> materialOutDetailList = materialOutDetailMapper.staticOutBillByTotalFee(queryParam);		
		assertEquals(true, materialOutDetailList.size() > 0);
	}
	
	
	
}
