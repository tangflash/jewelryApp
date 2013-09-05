package com.flash.jewelry.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MateralInfInputControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		//this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(new  MateralInfInputController()).build();
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void showAddPage2() throws Exception {
		mockMvc.perform(get("/materalInfInput/showAddPage2"))
			.andExpect(status().isOk())
			//.andExpect(content().contentType("text/plain;charset=UTF-8"))
			.andExpect(content().string("materalInfInput"));
	}

}
