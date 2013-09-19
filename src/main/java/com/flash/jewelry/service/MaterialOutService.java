package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.Client;
import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;
import com.flash.jewelry.model.ProductStyle;

public interface MaterialOutService {
	
	void updateMaterialOut(MaterialOut MaterialOut);

	void insertMaterialOut(MaterialOut MaterialOut);

	void updateMaterialOutDetail(MaterialOutDetail materialOutDetail);

	void insertMaterialOutDetail(MaterialOutDetail materialOutDetail);

	boolean isBillNumRepeat(String billNum, long id);

	boolean isBillMaterialRepeat(long materId, long billId,long id);
	
	void setMaterialId(MaterialOutDetail materialOutDetail);
	
	Collection<MaterialOutDetail> findMateriallOut(MaterialInQueryParam queryParam);
	
	Collection<MaterialOutDetail> staticOutBillByProduct(long outBillId);

	Collection<MaterialOutDetail> staticOutBillByMainMaterial(long materialOutBillId);

	Collection<MaterialOutDetail> staticOutBillBySecMaterial(long materialOutBillId);

	Collection<MaterialOutDetail> staticOutBillByTotalFee(long materialOutBillId);
	
	void submitBill(long billId);

	void deleteMaterialOut(Long billId);
	
	public void deleteMaterialOutDetail(long id);

	Client getClientByNumOrName(String clientName);

	GoldType getGoldTypeByNumOrName(String goldTypeName);

	ProductStyle getStyleByNumOrName(String styleName);

	MaterialOutDetail selectMaterialOutDetailById(String id);

	MaterialOut selectMaterialOutById(String string);

	Collection<MaterialOut> selectByBalanceBillId(long id);

	Collection<MaterialOut> selectNoBalanceBill(long clientId);

	void updateBalanceId(long balanceBillId, List materialOutBillIdList);

	void deleteLinkedBalanceBills(long billId);
}
