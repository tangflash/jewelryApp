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
	 * �����㵥Id��ѯ
	 * @param id
	 * @return
	 */
	Collection<MaterialOut> selectByBalanceBillId(long id);
	/**
	 * ��ѯδ����ĳ�����
	 * @param clientId
	 * @return
	 */
	Collection<MaterialOut> selectNoBalanceBill(long clientId);
	/**
	 * ���³������Ľ��㵥Id
	 * @param key -> balanceId
	 * @param key -> materialOutBillIds value format: 1,2,3
	 */
	void updateBalanceId(Map paramMap);
	/**
	 * ɾ�������Ľ��㵥Id
	 * @param billId
	 */
	void deleteLinkedBalanceBills(long billId);
	/**
	 * 
	 * @param billId
	 */
	void updateStatusByBalanceBillId(long billId);
}
