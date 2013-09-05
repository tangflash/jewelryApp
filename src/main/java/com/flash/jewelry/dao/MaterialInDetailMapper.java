package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;

public interface MaterialInDetailMapper {


	MaterialInDetail selectMaterialInDetailById(long id);

	void deleteMaterialInDetail(long id);

	void insertMaterialInDetail(MaterialInDetail materialInDetail);

	void updateMaterialInDetail(MaterialInDetail materialInDetail);

	Collection<MaterialIn> selectMaterialInDetail();

	MaterialInDetail selectMaterialInDetailByBillId(long i);

	int isBillMaterialRepeat(MaterialInDetail materialInDetail);

}
