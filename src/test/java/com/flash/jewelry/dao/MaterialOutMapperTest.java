package com.flash.jewelry.dao;

import static org.junit.Assert.*;

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
import com.flash.jewelry.model.BillStatus;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialOutMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private MaterialOutMapper materialOutMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralOutfOutputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteMaterialOut() throws Exception {
		int id = 1;
		materialOutMapper.deleteMaterialOut(id);
		MaterialOut materialOut = materialOutMapper.selectMaterialOutById(id);
		assertNull(materialOut);
	}

	@Test
	public void insertMaterialOut() throws ParseException {
		MaterialOut materialOut = new MaterialOut();
		materialOut.setBillNumber("13050401");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		materialOut.setBizDate(format.parse("2013-05-04"));
		materialOut.setGoldPrice(new BigDecimal(1));
		materialOut.setCreateTime(new Date());
		BillStatus billStatus = new BillStatus();
		billStatus.setNumber("01");
		materialOut.setBillStatus(billStatus);
		materialOutMapper.insertMaterialOut(materialOut);
		materialOut = materialOutMapper.selectMaterialOutByNumber("13050401");
		assertEquals("13050401", materialOut.getBillNumber());
	}

	@Test
	public void updateMaterialOut() {
		long id = 1;
		MaterialOut materialOut = materialOutMapper.selectMaterialOutById(id);

		materialOut.setBillNumber("13061602");
		materialOutMapper.updateMaterialOut(materialOut);
		materialOut = materialOutMapper.selectMaterialOutById(id);
		assertEquals("13061602", materialOut.getBillNumber());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;

		// insert one row Material
		MaterialOut materialOut = new MaterialOut();
		materialOut.setId(0);
		materialOut.setBillNumber("13061601");
		int actulVal = materialOutMapper.repeatRowByNumber(materialOut);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;

		MaterialOut materialOut = new MaterialOut();
		materialOut.setId(1);
		materialOut.setBillNumber("13061601");

		int actulVal = materialOutMapper.repeatRowByNumber(materialOut);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void selectMaterial() {
		int expectVal = 3;

		Collection<MaterialOut> list = materialOutMapper.selectMaterialOut();
		// assertEquals(expectVal, list.size());
		assertTrue(list.size() >= expectVal);

	}

	@Test
	public void selectMaterialById() {
		// insert two row data
		MaterialOut materialOut = materialOutMapper.selectMaterialOutById(1);
		// assertEquals(expectVal, list.size());
		assertEquals(1, materialOut.getId());
		assertEquals("Íø¼Í",materialOut.getClientName());
		assertEquals(1,materialOut.getClientId());
		assertEquals("18K°×",materialOut.getGoldTypeName());
		assertEquals(1,materialOut.getGoldTypeId());

	}
	
	@Test
	public void submitBill(){
		materialOutMapper.submitBill(1);
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		queryParam.setBillNumber("13061601");
		//Collection<MaterialOut> materialOutList = materialOutMapper.findMateriallOut(queryParam);
		/*Iterator<MaterialOut> MaterialOutIter = materialOutList.iterator();
		MaterialOut materialOut = MaterialOutIter.next();
		assertEquals("1",materialOut.getBillStatus().getNumber());*/
	}
}
