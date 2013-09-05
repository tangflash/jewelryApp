package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.MaterialMapper;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.MaterialManagerService;

public class MaterialManager implements MaterialManagerService {
	
	@Autowired
	private MaterialMapper materialMapper = null;
	
	
	public MaterialManager(String abc){
		System.out.println(abc);
	}
	public Collection<Material> selectMaterial(List list) {		
		return materialMapper.selectMaterial();
	}

	public void updateMaterial(Material material) {
		materialMapper.updateMaterial(material);
	}

	public void deleteMaterial(long id) {
		materialMapper.deleteMaterial(id);
	}

	public void insertMaterial(Material material) {
		materialMapper.insertMaterial(material);

	}

	public boolean isRepeatByNum(Material material) {
		//Material material = materialMapper.selectMaterialByNumber(number);
		int rowCount = materialMapper.repeatRowByNumber(material);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public Material selectMaterialById(long id) {
		return materialMapper.selectMaterialById(id);
	}
	public Material selectMaterialByNum(String num) {		
		return materialMapper.selectMaterialByNumber(num);
	}
	public boolean isRepeatByName(Material material) {
		int rowCount = materialMapper.repeatRowByName(material);
		if (rowCount > 0)
			return true;
		else
			return false;
	}
	

}
