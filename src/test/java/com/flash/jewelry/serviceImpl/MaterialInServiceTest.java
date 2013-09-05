package com.flash.jewelry.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.service.MaterialInService;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialInServiceTest extends AbstractContextControllerTests {
	@Autowired
	private MaterialInService materialInService;
	
	@Test
	public void isBillNumRepeat() {		
		boolean actulVal1 = materialInService.isBillNumRepeat("13061601",0);
		assertEquals(true,actulVal1);
		
		boolean actulVal2 = materialInService.isBillNumRepeat("13061601",1);
		assertEquals(false,actulVal2);
	}
	
	@Test
	public void isBillMaterialRepeat() {		
		boolean actulVal1 = materialInService.isBillMaterialRepeat(1,28,0);
		assertEquals(true,actulVal1);
		
		boolean actulVal2 = materialInService.isBillMaterialRepeat(1,1,1);
		assertEquals(false,actulVal2);
	}	
	
}
