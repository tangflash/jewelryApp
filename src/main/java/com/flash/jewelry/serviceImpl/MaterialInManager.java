package com.flash.jewelry.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.MaterialInDetailMapper;
import com.flash.jewelry.dao.MaterialInMapper;
import com.flash.jewelry.dao.MaterialMapper;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialInService;

public class MaterialInManager implements MaterialInService {

	@Autowired
	private MaterialInMapper materialInMapper;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private MaterialInDetailMapper materialInDetailMapper;
	@Autowired
	private InventoryManagerService inventoryManagerService;
	
	
	public void updateMaterialIn(MaterialIn materialIn) {
		materialInMapper.updateMaterialIn(materialIn);		
	}

	public void insertMaterialIn(MaterialIn materialIn) {
		materialInMapper.insertMaterialIn(materialIn);		
	}
	
	public void setMaterialId(MaterialInDetail materialInDetail){
		if ((materialInDetail.getMaterId() == 0) && (materialInDetail.getMaterNum().trim() != null)) {
			Material materialInfo = materialMapper.selectMaterialByNumber(materialInDetail.getMaterNum().trim());
			materialInDetail.setMaterId(materialInfo.getId());
		}
			
	}
	
	public void updateMaterialInDetail(MaterialInDetail materialInDetail) {
		//setMaterialId(materialInDetail);
		materialInDetailMapper.updateMaterialInDetail(materialInDetail);		
	}

	public void insertMaterialInDetail(MaterialInDetail materialInDetail) {
		//setMaterialId(materialInDetail);
		materialInDetailMapper.insertMaterialInDetail(materialInDetail);		
	}

	public boolean isBillNumRepeat(String billNum, long id) {
		MaterialIn materialIn = new MaterialIn();
		materialIn.setId(id);		
		materialIn.setBillNumber(billNum);
		int rowCount = materialInMapper.repeatRowByNumber(materialIn);
		return rowCount > 0?true:false;
	}

	public boolean isBillMaterialRepeat(long materId, long billId,long id) {
		MaterialInDetail materialInDetail = new MaterialInDetail();
		materialInDetail.setId(id);
		materialInDetail.setMaterId(materId);
		materialInDetail.setBillId(billId);
		int rowCount = materialInDetailMapper.isBillMaterialRepeat(materialInDetail);
		return rowCount > 0?true:false;
	}

	public Collection<MaterialIn> findMateriallIn(
			MaterialInQueryParam queryParam) {		
		return materialInMapper.findMateriallIn(queryParam);
	}

	public void submitBill(long billId) {
		materialInMapper.submitBill(billId);
	}

	public void deleteMaterialIn(long id) {
		materialInMapper.deleteMaterialIn(id);		
	}
	
	public void deleteMaterialInDetail(long id){
		materialInDetailMapper.deleteMaterialInDetail(id);
	}

	public MaterialIn selectMaterialInById(String id) {
		return materialInMapper.selectMaterialInById(Long.valueOf(id));
	}
	
	public MaterialInDetail selectMaterialInDetailById(String id) {
		return materialInDetailMapper.selectMaterialInDetailById(Long.valueOf(id));
	}
}
