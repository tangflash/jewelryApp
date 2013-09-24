package com.flash.jewelry.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;

public interface MaterialOutMapper {

	void deleteMaterialOut(long id);
	MaterialOut selectMaterialOutById(long id);
	void insertMaterialOut(MaterialOut MaterialOut);
	MaterialOut selectMaterialOutByNumber(String string);
	void updateMaterialOut(MaterialOut MaterialOut);
	int repeatRowByNumber(MaterialOut MaterialOut);
	Collection<MaterialOut> selectMaterialOut();	
	void submitBill(long billId);
	
	void unSubmitBill(long billId);
	/**
	 * 按结算单Id查询
	 * @param id
	 * @return
	 */
	Collection<MaterialOut> selectByBalanceBillId(long id);
	/**
	 * 查询未结算的出货单
	 * @param clientId
	 * @return
	 */
	Collection<MaterialOut> selectNoBalanceBill(long clientId);
	/**
	 * 更新出货单的结算单Id
	 * @param key -> balanceId
	 * @param key -> materialOutBillIds value format: 1,2,3
	 */
	void updateBalanceId(Map paramMap);
	/**
	 * 删除关联的结算单Id
	 * @param billId
	 */
	void deleteLinkedBalanceBills(long billId);
	/**
	 * 
	 * @param billId
	 */
	void updateStatusByBalanceBillId(long billId);
}
