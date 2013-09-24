package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.BalanceBill;
import com.flash.jewelry.model.BalanceBillQueryParam;

public interface BalanceBillMapper {
	void delete(long id);
	
	BalanceBill selectById(long id);
	
	void insert(BalanceBill balanceBill);		
	
	int repeatRowByNumber(BalanceBill balanceBill);		
	
	Collection<BalanceBill> find(BalanceBillQueryParam queryParam);
	
	void submitBill(long billId);

	int checkNoSubmitBillCount(long clientId);
	
	void updateInventoryByDelBalanceBill(long balanceBillId);
}
