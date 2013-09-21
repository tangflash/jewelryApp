package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.BaseData;

public interface BaseDataMapper {
	
	Collection<BaseData> selectByTypeNum(String typeNum);
	
}
