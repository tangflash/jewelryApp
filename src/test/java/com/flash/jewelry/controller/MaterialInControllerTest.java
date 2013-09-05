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
public class MaterialInControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		//this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		//this.mockMvc = MockMvcBuilders.standaloneSetup(new  MaterialInController()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void showPage() throws Exception {
		mockMvc.perform(get("/materialInBillEdit/showPage"))
			.andExpect(status().isOk())			
			.andExpect(view().name("materialInBillEdit"))
			.andExpect(model().attributeExists("materialIn"));
	}
	
	@Test
	public void doSaveBillHead() throws Exception {		
		mockMvc.perform(post("/materialInBillEdit/doSaveBillHead")
				.param("id", "1")
				.param("billNumber", "0001")
				.param("bizDate", "2013-06-22")
				.param("createTime", "2013-06-22 10:57:01")
				
				.flashAttr("materialInDetail.id", "0")
				.flashAttr("materialInDetail.billId", "1")
				.flashAttr("materialInDetail.materId", "")
				.flashAttr("materialInDetail.materNum", "W5")
				.flashAttr("materialInDetail.amount", "10")
				.flashAttr("materialInDetail.weight", "100")
				.flashAttr("materialInDetail.sort", "0")
				)
			.andExpect(status().isOk())			
			.andExpect(view().name("materialInBillEdit"))			
			.andExpect(model().attribute("successMessage", "保存单据成功!"));
	}	
	

}
