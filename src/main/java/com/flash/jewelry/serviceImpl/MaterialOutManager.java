package com.flash.jewelry.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.MaterialMapper;
import com.flash.jewelry.dao.MaterialOutDetailMapper;
import com.flash.jewelry.dao.MaterialOutMapper;
import com.flash.jewelry.model.Client;
import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.ClientService;
import com.flash.jewelry.service.GoldTypeService;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialOutService;
import com.flash.jewelry.service.ProductStyleService;

public class MaterialOutManager implements MaterialOutService {

	@Autowired
	private MaterialOutMapper materialOutMapper;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private MaterialOutDetailMapper materialOutDetailMapper;
	@Autowired
	private ClientService clientService;
	@Autowired
	private GoldTypeService goldTypeService;
	@Autowired
	private ProductStyleService productStyleService;
	@Autowired
	private InventoryManagerService inventoryManagerService;
	
	public void updateMaterialOut(MaterialOut materialOut) {
		materialOutMapper.updateMaterialOut(materialOut);		
	}

	public void insertMaterialOut(MaterialOut materialOut) {
		materialOutMapper.insertMaterialOut(materialOut);		
	}
	
	public void setMaterialId(MaterialOutDetail materialOutDetail){
		if ((materialOutDetail.getMaterId() == 0) && (materialOutDetail.getMaterName() != null)) {
			Material materialOutfo = materialMapper.selectMaterialByNumber(materialOutDetail.getMaterName().trim());
			materialOutDetail.setMaterId(materialOutfo.getId());
		}
			
	}
	
	public void updateMaterialOutDetail(MaterialOutDetail materialOutDetail) {
		//setMaterialId(materialOutDetail);
		materialOutDetailMapper.updateMaterialOutDetail(materialOutDetail);		
	}

	public void insertMaterialOutDetail(MaterialOutDetail materialOutDetail) {
		//setMaterialId(materialOutDetail);
		materialOutDetailMapper.insertMaterialOutDetail(materialOutDetail);		
	}

	public boolean isBillNumRepeat(String billNum, long id) {
		MaterialOut materialOut = new MaterialOut();
		materialOut.setId(id);
		materialOut.setBillNumber(billNum);
		int rowCount = materialOutMapper.repeatRowByNumber(materialOut);
		return rowCount > 0?true:false;
	}

	public boolean isBillMaterialRepeat(long materId, long billId,long id) {
		MaterialOutDetail materialOutDetail = new MaterialOutDetail();
		materialOutDetail.setId(id);
		materialOutDetail.setMaterId(materId);
		materialOutDetail.setBillId(billId);
		int rowCount = materialOutDetailMapper.isBillMaterialRepeat(materialOutDetail);
		return rowCount > 0?true:false;
	}

	public Collection<MaterialOutDetail> findMateriallOut(MaterialInQueryParam queryParam){
			return materialOutDetailMapper.findMateriallOut(queryParam);
	}
	
	public Collection<MaterialOutDetail> staticOutBillByProduct(MaterialInQueryParam queryParam){
		return materialOutDetailMapper.staticOutBillByProduct(queryParam);
	}

	public Collection<MaterialOutDetail> staticOutBillByMainMaterial(MaterialInQueryParam queryParam){
		return materialOutDetailMapper.staticOutBillByMainMaterial(queryParam);
	}

	public Collection<MaterialOutDetail> staticOutBillBySecMaterial(MaterialInQueryParam queryParam){
		return materialOutDetailMapper.staticOutBillBySecMaterial(queryParam);
	}

	public Collection<MaterialOutDetail> staticOutBillByTotalFee(MaterialInQueryParam queryParam){
		return materialOutDetailMapper.staticOutBillByTotalFee(queryParam);
	}

	public void submitBill(long billId) {
		materialOutMapper.submitBill(billId);
		 MaterialOut  materialOut  = materialOutMapper.selectMaterialOutById(billId);
		inventoryManagerService.submitMaterialOut(materialOut);		
	}

	public void deleteMaterialOut(Long billId) {
		materialOutMapper.deleteMaterialOut(billId);		
	}

	public void deleteMaterialOutDetail(long id) {
		materialOutDetailMapper.deleteMaterialOutDetail(id);		
	}

	public Client getClientByNumOrName(String clientName) {
		return clientService.getClientByNumOrName(clientName);
	}

	public GoldType getGoldTypeByNumOrName(String goldTypeName) {
		return goldTypeService.getGoldTypeByNumOrName(goldTypeName);
	}

	public ProductStyle getStyleByNumOrName(String styleName) {		
		return productStyleService.getStyleByNumOrName(styleName);
	}

	public MaterialOutDetail selectMaterialOutDetailById(String id) {
		return materialOutDetailMapper.selectMaterialOutDetailById(Long.valueOf(id));
	}

	public MaterialOut selectMaterialOutById(String id) {
		return materialOutMapper.selectMaterialOutById(Long.valueOf(id));
	}

}
