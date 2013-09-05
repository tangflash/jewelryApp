package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.GoldType;

public interface GoldTypeService {
	Collection<GoldType> selectGoldType(List list);
	GoldType selectGoldTypeById(long id);
	void updateGoldType(GoldType goldType); 
	void deleteGoldType(long id);
	void insertGoldType(GoldType goldType);
	boolean isRepeatByNum(GoldType goldType);
	boolean isRepeatByName(GoldType goldType);
	GoldType selectGoldTypeByNum(String num);
	GoldType getGoldTypeByNumOrName(String goldTypeName);
}
