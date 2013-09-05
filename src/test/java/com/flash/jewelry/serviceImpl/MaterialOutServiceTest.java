package com.flash.jewelry.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.service.MaterialOutService;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialOutServiceTest extends AbstractContextControllerTests {
	@Autowired
	private MaterialOutService materialOutService;
	
	@Test
	public void isBillNumRepeat() {		
		boolean actulVal1 = materialOutService.isBillNumRepeat("13061601",0);
		assertEquals(true,actulVal1);
		
		boolean actulVal2 = materialOutService.isBillNumRepeat("13061601",1);
		assertEquals(false,actulVal2);
	}
	
	@Test
	public void isBillMaterialRepeat() {		
		boolean actulVal1 = materialOutService.isBillMaterialRepeat(1,28,0);
		assertEquals(true,actulVal1);
		
		boolean actulVal2 = materialOutService.isBillMaterialRepeat(1,1,1);
		assertEquals(false,actulVal2);
	}	
	
}
