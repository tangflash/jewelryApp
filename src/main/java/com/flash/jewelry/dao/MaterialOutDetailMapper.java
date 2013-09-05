package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;

public interface MaterialOutDetailMapper {

	MaterialOutDetail selectMaterialOutDetailById(long id);

	void deleteMaterialOutDetail(long id);

	void insertMaterialOutDetail(MaterialOutDetail MaterialOutDetail);

	void updateMaterialOutDetail(MaterialOutDetail MaterialOutDetail);

	Collection<MaterialOut> selectMaterialOutDetail();

	MaterialOutDetail selectMaterialOutDetailByBillId(long i);

	int isBillMaterialRepeat(MaterialOutDetail MaterialOutDetail);
	
	Collection<MaterialOutDetail> findMateriallOut(MaterialInQueryParam queryParam);

	Collection<MaterialOutDetail> staticOutBillByProduct(MaterialInQueryParam queryParam);

	Collection<MaterialOutDetail> staticOutBillByMainMaterial(MaterialInQueryParam queryParam);

	Collection<MaterialOutDetail> staticOutBillBySecMaterial(MaterialInQueryParam queryParam);

	Collection<MaterialOutDetail> staticOutBillByTotalFee(MaterialInQueryParam queryParam);
}
