package com.flash.jewelry.service;

import java.util.Collection;

import com.flash.jewelry.model.BalanceBill;
import com.flash.jewelry.model.BalanceBillQueryParam;
import com.flash.jewelry.model.BalanceMainMaterDetail;

public interface BalanceBillService {

	BalanceBill selectById(String id);

	Collection<BalanceMainMaterDetail> selectByBillId(String id);

	void insert(BalanceBill balanceBill);

	void iniMainMaterDetail(long balanceBillId, long clientId);

	boolean isExistNoSubmitBill(long clientId);

	Collection<BalanceBill> find(BalanceBillQueryParam queryParam);

	void submitBill(long billId);

	void delete(long billId);
	
	void updateInventoryByDelBalanceBill(long balanceBillId);

}
