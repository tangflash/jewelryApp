package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.BalanceBillMapper;
import com.flash.jewelry.dao.BalanceMainMaterDetailMapper;
import com.flash.jewelry.model.BalanceBill;
import com.flash.jewelry.model.BalanceBillQueryParam;
import com.flash.jewelry.model.BalanceMainMaterDetail;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.service.BalanceBillService;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialOutService;

public class BalanceBillManager implements BalanceBillService {
	@Autowired
	private BalanceBillMapper balanceBillMapper;
	@Autowired
	private BalanceMainMaterDetailMapper balanceMainMaterDetailMapper;
	@Autowired
	private MaterialOutService materialOutService;
	@Autowired
	private InventoryManagerService inventoryManagerService;
	
	public BalanceBill selectById(String id) {
		return balanceBillMapper.selectById(Long.valueOf(id));
	}

	public Collection<BalanceMainMaterDetail> selectByBillId(String id) {
		return balanceMainMaterDetailMapper.selectByBillId(Long.valueOf(id));
	}

	public void insert(BalanceBill balanceBill) {
		balanceBillMapper.insert(balanceBill);
	}

	public void iniMainMaterDetail(long balanceBillId, long clientId) {
		Map params = new HashMap<String, String>();
		params.put("balanceBillId", "" + balanceBillId);
		params.put("clientId", "" + clientId);
		balanceMainMaterDetailMapper.iniMainMaterDetail(params);
	}

	public boolean isExistNoSubmitBill(long clientId) {
		int recCount = balanceBillMapper.checkNoSubmitBillCount(clientId);
		return recCount > 0;
	}

	public Collection<BalanceBill> find(BalanceBillQueryParam queryParam) {
		return balanceBillMapper.find(queryParam);
	}

	public void submitBill(long billId) {
		balanceBillMapper.submitBill(billId);		
		BalanceBill balanceBill = selectById("" + billId);
		inventoryManagerService.balanceInventory(balanceBill.getClientId());
	}

	public void delete(long billId) {		
		materialOutService.updateStatusByBalanceBillId(billId);
		materialOutService.deleteLinkedBalanceBills(billId);		
		balanceBillMapper.delete(billId);		
	}

	public void updateInventoryByDelBalanceBill(long balanceBillId) {
		balanceBillMapper.updateInventoryByDelBalanceBill(balanceBillId);		
	}

}
