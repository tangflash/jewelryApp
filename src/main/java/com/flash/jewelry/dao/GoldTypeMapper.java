package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.GoldType;

public interface GoldTypeMapper {
	Collection<GoldType> selectGoldType();
	void updateGoldType(GoldType goldType); 
	void deleteGoldType(long id);
	void insertGoldType(GoldType goldType);	
	GoldType selectGoldTypeByNumber(String number);
	GoldType selectGoldTypeById(long id);
	int repeatRowByNumber(GoldType goldType);	
	int repeatRowByName(GoldType goldType);
	GoldType getGoldTypeByNumOrName(String goldTypeName);
}
