package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.controller.City;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.ProductStyle;

public interface MaterialManagerService {
	Collection<Material> selectMaterial(List list);
	Material selectMaterialById(long id);
	void updateMaterial(Material material); 
	void deleteMaterial(long id);
	void insertMaterial(Material material);
	boolean isRepeatByNum(Material material);
	boolean isRepeatByName(Material material);
	Material selectMaterialByNum(String num);	
}
