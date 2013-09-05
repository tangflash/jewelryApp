package com.flash.jewelry.serviceImpl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.service.MaterialManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaterialManagerServiceTest extends AbstractContextControllerTests {
	@Autowired
	private MaterialManagerService materialManagerService;
	
	@Test
	public void selectMaterialByNum(){
		String num = "W5";
		Material material = materialManagerService.selectMaterialByNum(num);
		assertNotNull(material);
	}	
}
