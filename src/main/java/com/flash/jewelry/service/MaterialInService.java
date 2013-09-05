package com.flash.jewelry.service;

import java.util.Collection;

import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;
import com.flash.jewelry.model.MaterialInQueryParam;

public interface MaterialInService {
	
	void updateMaterialIn(MaterialIn materialIn);

	void insertMaterialIn(MaterialIn materialIn);

	void updateMaterialInDetail(MaterialInDetail materialInDetail);

	void insertMaterialInDetail(MaterialInDetail materialInDetail);

	boolean isBillNumRepeat(String billNum, long id);

	boolean isBillMaterialRepeat(long materId, long billId,long id);
	
	void setMaterialId(MaterialInDetail materialInDetail);
	
	Collection<MaterialIn> findMateriallIn(MaterialInQueryParam queryParam);
	void submitBill(long billId);
	
	void deleteMaterialIn(long id);
	
	void deleteMaterialInDetail(long id);

	MaterialIn selectMaterialInById(String id);	
	
	MaterialInDetail selectMaterialInDetailById(String id);
}
