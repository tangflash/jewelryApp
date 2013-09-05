package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.GoldTypeMapper;
import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.service.GoldTypeService;

public class GoldTypeManager implements GoldTypeService {
	
	@Autowired
	private GoldTypeMapper goldTypeMapper = null;
	
	
	public GoldTypeManager(){		
	}
	public Collection<GoldType> selectGoldType(List list) {		
		return goldTypeMapper.selectGoldType();
	}

	public void updateGoldType(GoldType goldType) {
		goldTypeMapper.updateGoldType(goldType);
	}

	public void deleteGoldType(long id) {
		goldTypeMapper.deleteGoldType(id);
	}

	public void insertGoldType(GoldType goldType) {
		goldTypeMapper.insertGoldType(goldType);

	}

	public boolean isRepeatByNum(GoldType goldType) {
		//goldType goldType = GoldTypeMapper.selectgoldTypeByNumber(number);
		int rowCount = goldTypeMapper.repeatRowByNumber(goldType);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public GoldType selectGoldTypeById(long id) {
		return goldTypeMapper.selectGoldTypeById(id);
	}
	public GoldType selectGoldTypeByNum(String num) {		
		return goldTypeMapper.selectGoldTypeByNumber(num);
	}
	public boolean isRepeatByName(GoldType goldType) {
		int rowCount = goldTypeMapper.repeatRowByName(goldType);
		if (rowCount > 0)
			return true;
		else
			return false;
	}
	public GoldType getGoldTypeByNumOrName(String goldTypeName) {
		return goldTypeMapper.getGoldTypeByNumOrName(goldTypeName);
	}	

}
