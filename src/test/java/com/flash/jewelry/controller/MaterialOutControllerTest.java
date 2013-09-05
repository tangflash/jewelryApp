package com.flash.jewelry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialOutControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		//this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		//this.mockMvc = MockMvcBuilders.standaloneSetup(new  MaterialOutController()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void showPage() throws Exception {
		mockMvc.perform(get("/materialOutBillEdit/showPage"))
			.andExpect(status().isOk())			
			.andExpect(view().name("materialOutBillEdit"))
			.andExpect(model().attributeExists("materialOut"));
	}
	
	@Test
	public void doSaveBillHead() throws Exception {		
		mockMvc.perform(post("/materialOutBillEdit/doSaveBillHead")
				.param("id", "1")
				.param("billNumber", "0001")
				.param("goldTypeName", "18k")
				.param("goldPrice", "220")
				.param("clientName", "01")				
				.param("bizDate", "2013-06-22")
				.param("createTime", "2013-06-22 10:57:01")	
				.param("billStatus.number", "0")	
				
				.param("materialOutDetail.styleName", "AT0611")
				.param("materialOutDetail.handSize", "12.1")
				.param("materialOutDetail.productAmount", "5")
				.param("materialOutDetail.productWeight", "500")
				.param("materialOutDetail.glodWeight", "400")
				.param("materialOutDetail.consumeWeight", "600")
				.param("materialOutDetail.processCost", "40.5")
				.param("materialOutDetail.addProcessCost", "11.2")
				.param("materialOutDetail.superSetCost", "1.2")
				.param("materialOutDetail.factoryAddMoney", "200")
				
				.param("materialOutDetail.id", "0")
				.param("materialOutDetail.billId", "1")
				.param("materialOutDetail.materId", "0")
				.param("materialOutDetail.materName", "W5")
				.param("materialOutDetail.amount", "10")
				.param("materialOutDetail.weight", "100")
				.param("materialOutDetail.sort", "0")
				
				.param("materialOutDetail.secMaterId", "0")
				.param("materialOutDetail.secMaterNum", "FM520")
				.param("materialOutDetail.SecAmount", "5")
				.param("materialOutDetail.SecWeight", "100.5")
				.param("materialOutDetail.secPrice", "210.5")
				)
			.andExpect(status().isOk())			
			.andExpect(view().name("materialOutBillEdit"))			
			.andExpect(model().attribute("successMessage", "保存单据成功!"));
	}	
	

}
