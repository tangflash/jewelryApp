package com.flash.jewelry.dao;

import java.util.Collection;
import java.util.Map;

import com.flash.jewelry.model.BalanceMainMaterDetail;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;

public interface BalanceMainMaterDetailMapper {


	BalanceMainMaterDetail selectById(long id);	

	void insert(BalanceMainMaterDetail balanceMainMaterDetail);		

	Collection<BalanceMainMaterDetail> selectByBillId(long billId);
	/**
	 * 
	 * @param params key -> balanceBillId
	 * @param params key -> clientId
	 */
	void iniMainMaterDetail(Map params);	

}
