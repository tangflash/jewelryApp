package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.Material;

public interface MaterialMapper {
	Collection<Material> selectMaterial();
	void updateMaterial(Material material); 
	void deleteMaterial(long id);
	void insertMaterial(Material material);	
	Material selectMaterialByNumber(String number);
	Material selectMaterialById(long id);
	int repeatRowByNumber(Material material);
	int repeatRowByName(Material material);
}
