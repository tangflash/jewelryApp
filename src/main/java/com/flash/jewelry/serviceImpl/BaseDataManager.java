package com.flash.jewelry.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.BaseDataMapper;
import com.flash.jewelry.model.BaseData;
import com.flash.jewelry.service.BaseDataService;

public class BaseDataManager implements BaseDataService {
	private static final String CODE_FACTORY_NAME = "02";
	private static final String CODE_MAKE_BILL_PERSON = "03";
	
	@Autowired
	BaseDataMapper baseDataMapper;

	public String getFactoryName() {
		Collection<BaseData> list = baseDataMapper.selectByTypeNum(CODE_FACTORY_NAME);
		if (list.size() < 1) return "";
		for (BaseData baseData : list) {
			return baseData.getName();			
		}
		return "";		
	}

	public String getMakeBillPerson() {
		Collection<BaseData> list = baseDataMapper.selectByTypeNum(CODE_MAKE_BILL_PERSON);
		if (list.size() < 1) return "";
		for (BaseData baseData : list) {
			return baseData.getName();			
		}
		return "";
	}

}
