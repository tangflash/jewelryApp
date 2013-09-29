package com.flash.jewelry.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flash.jewelry.common.StringUtil;
import com.flash.jewelry.model.Client;
import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.BaseDataService;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialManagerService;
import com.flash.jewelry.service.MaterialOutService;

@Controller
@RequestMapping("/materialOutBill*")
public class MaterialOutController {

	private final static String VIEW_EDIT_PAGE = "materialOutBillEdit";
	private final static String VIEW_LIST_PAGE = "materialOutBillList";
	private final static String VIEW_LIST_REPORT_PAGE = "materialOutBillReport";
	private final static String VIEW_LIST_TOTAL_REPORT_PAGE = "materialOutBillTotalReport";
	private final static String VIEW_TOTAL_LIST_PAGE = "materialOutTotalBillList";

	@Autowired
	private MaterialOutService materialOutService;
	@Autowired
	private MaterialManagerService materialManagerService;
	@Autowired
	BaseDataService baseDataService;

	

	@RequestMapping("/showPage")
	public ModelAndView showPage(String id) {
		ModelAndView modelAndView = new ModelAndView();
		
		MaterialOut materialOut = null;
		if (id == null || id.equals("")) {
			materialOut = new MaterialOut();
			materialOut.setId(0);
			// SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");
			// dateFormat.f
			materialOut.setBizDate(new Date());
			materialOut.setCreateTime(new Date());
			MaterialOutDetail materialOutDetail = new MaterialOutDetail();
			materialOutDetail.setNumber(1);
			materialOutDetail.setProductAmount(1);
			materialOutDetail.setFactoryAddMoney(new BigDecimal(0));
			materialOutDetail.setWeight(new BigDecimal(0));
			materialOutDetail.setSecWeight(new BigDecimal(0));
			materialOutDetail.setSecPrice(new BigDecimal(0));
			materialOutDetail.setTemplateFree(new BigDecimal(0));
			materialOutDetail.setAmount(1);
			materialOut.setMaterialOutDetail(materialOutDetail);
		}
		else{
			MaterialOutDetail materialOutDetail = materialOutService.selectMaterialOutDetailById(id);		
			materialOut = materialOutService.selectMaterialOutById("" + materialOutDetail.getBillId());		
			materialOut.setMaterialOutDetail(materialOutDetail);
		}

		modelAndView.setViewName(VIEW_EDIT_PAGE);
		modelAndView.addObject("materialOut", materialOut);	

		return modelAndView;
	}

	protected String saveBillHead(MaterialOut materialOut) {
		String errorMessage = null;
		long id = 0;
		if (materialOut.getId() > 0)
			id = materialOut.getId();

		if (materialOutService.isBillNumRepeat(materialOut.getBillNumber(), id)) {
			errorMessage = "单据编码不能重复!";
			return errorMessage;
		}
		
		Client client = materialOutService.getClientByNumOrName(materialOut.getClientName());
		if (client == null) {
			errorMessage = "客户编码/名称" + materialOut.getClientName() + "不存在!";
			return errorMessage;
		}
		materialOut.setClientId(client.getId());
		
		GoldType goldType = materialOutService.getGoldTypeByNumOrName(materialOut.getGoldTypeName());
		if (goldType == null) {
			errorMessage = "金成色编码/名称" + materialOut.getGoldTypeName() + "不存在!";
			return errorMessage;
		}
		materialOut.setGoldTypeId(goldType.getId());
		
		if (materialOut.getId() > 0)
			materialOutService.updateMaterialOut(materialOut);
		else
			materialOutService.insertMaterialOut(materialOut);
		return errorMessage;
	}

	protected String saveBillDetail(MaterialOut materialOut) {
		String errorMessage = null;
		MaterialOutDetail materialOutDetail = materialOut
				.getMaterialOutDetail();
		materialOutDetail.setBillId(materialOut.getId());		
		
		ProductStyle productStyle = materialOutService.getStyleByNumOrName(materialOutDetail.getStyleName());
		if (productStyle == null) {
			errorMessage = "款式编码/名称" + materialOutDetail.getStyleName() + "不存在!";
			return errorMessage;
		}
		materialOutDetail.setStyleId(productStyle.getId());
		materialOutDetail.setProductNameId(productStyle.getProduct().getId());
		
		
		if (!StringUtil.isEmpty(materialOutDetail.getMaterName())){
			Material material = materialManagerService.selectMaterialByNum(materialOutDetail.getMaterName());
			if (material == null) {
				errorMessage = "主石编码/名称" + materialOutDetail.getMaterName() + "不存在!";
				return errorMessage;
			}
			materialOutDetail.setMaterId(material.getId());
		}
		
		if (materialOutDetail.getSecMaterName() != null && !materialOutDetail.getSecMaterName().trim().equals("")){
			Material material = materialManagerService.selectMaterialByNum(materialOutDetail.getSecMaterName());
			if (material == null) {
				errorMessage = "副石编码/名称" + materialOutDetail.getSecMaterName() + "不存在!";
				return errorMessage;
			}
			materialOutDetail.setSecMaterId(material.getId());
		}
		
		materialOutService.setMaterialId(materialOutDetail);
		
		if (materialOutDetail.getId() > 0)
			materialOutService.updateMaterialOutDetail(materialOutDetail);
		else
			materialOutService.insertMaterialOutDetail(materialOutDetail);

		MaterialOutDetail newMaterialOutDetail = new MaterialOutDetail();		
		newMaterialOutDetail.setNumber(materialOutDetail.getNumber() + 1);
		newMaterialOutDetail.setProductAmount(1);
		newMaterialOutDetail.setFactoryAddMoney(new BigDecimal(0));
		newMaterialOutDetail.setWeight(new BigDecimal(0));
		newMaterialOutDetail.setSecWeight(new BigDecimal(0));
		newMaterialOutDetail.setSecPrice(new BigDecimal(0));
		newMaterialOutDetail.setLoss(materialOutDetail.getLoss());
		newMaterialOutDetail.setProcessCost(materialOutDetail.getProcessCost());
		newMaterialOutDetail.setTemplateFree(new BigDecimal(0));
		newMaterialOutDetail.setAmount(1);
		materialOut.setMaterialOutDetail(newMaterialOutDetail);
		return errorMessage;
	}

	@RequestMapping(value = "/doSaveBillHead", method = RequestMethod.POST)
	public ModelAndView doSaveBillHead(
			@Valid @ModelAttribute("materialOut") MaterialOut materialOut,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_EDIT_PAGE);

		if (result.hasErrors()) {
			modelAndView.addObject("materialOut", materialOut);
			return modelAndView;
		}
		
		if (StringUtil.isEmpty(materialOut.getMaterialOutDetail().getMaterName())
			&& StringUtil.isEmpty(materialOut.getMaterialOutDetail().getSecMaterName())){
			modelAndView.addObject("errorMessage", "主石号和副石号不能都为为空!");
			return modelAndView;
		}
		
		String errorMessage = null;
		errorMessage = saveBillHead(materialOut);
		if (errorMessage != null) {
			modelAndView.addObject("errorMessage", errorMessage);
			return modelAndView;
		}
		errorMessage = saveBillDetail(materialOut);
		if (errorMessage != null) {
			modelAndView.addObject("errorMessage", errorMessage);
			return modelAndView;
		}

		modelAndView.addObject("successMessage", "保存单据成功!");
		modelAndView.addObject("materialOut", materialOut);
		return modelAndView;
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(VIEW_LIST_PAGE);
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		modelAndView.addObject("queryParam", queryParam);

		return modelAndView;
	}
	
	@RequestMapping("/showTotalListPage")
	public ModelAndView showTotalListPage() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(VIEW_TOTAL_LIST_PAGE);
		MaterialInQueryParam queryParam = new MaterialInQueryParam();
		modelAndView.addObject("queryParam", queryParam);

		return modelAndView;
	}

	@RequestMapping("/findList")
	public ModelAndView findListPage(
			@Valid @ModelAttribute("queryParam") MaterialInQueryParam queryParam,
			BindingResult result, Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_LIST_PAGE);

		if (result.hasErrors()) {
			modelAndView.addObject("queryParam", queryParam);
			return modelAndView;
		}		
		
		String buttonExport = request.getParameter("exportButton");		
		Collection<MaterialOutDetail> list = materialOutService
				.findMateriallOut(queryParam);
		sumMaterialOutDetail(list);
		modelAndView.addObject("queryParam", queryParam);
		modelAndView.addObject("list", list);
		if ("exportButton".equals(buttonExport)){			
			modelAndView.addObject("datasource", list);
			modelAndView.addObject("format", queryParam.getExportFormat());	
			modelAndView.setViewName(VIEW_LIST_REPORT_PAGE);
			modelAndView.addObject("factoryName",baseDataService.getFactoryName());
			modelAndView.addObject("printTime", new Date());
			modelAndView.addObject("makeBillPerson", baseDataService.getMakeBillPerson());
			//modelAndView.setViewName("simpleReport");
		}
		return modelAndView;
	}
	
	private void sumMaterialOutDetail(Collection<MaterialOutDetail> list){
		MaterialOutDetail totalMaterialOutDetail = new MaterialOutDetail();
		MaterialOut materialOut = new MaterialOut();
		materialOut.getBillStatus().setNumber("-1");
		materialOut.getBillStatus().setName("");
		materialOut.setCreateTime(null);
		totalMaterialOutDetail.setMaterialOut(materialOut);
		totalMaterialOutDetail.setNumber(null);
		totalMaterialOutDetail.setProductWeight(new BigDecimal(0));
		totalMaterialOutDetail.setGoldWeight(new BigDecimal(0));
		totalMaterialOutDetail.setConsumeWeight(new BigDecimal(0));
		totalMaterialOutDetail.setGoldMoney(new BigDecimal(0));
		totalMaterialOutDetail.setProcessCost(new BigDecimal(0));
		totalMaterialOutDetail.setAddProcessCost(new BigDecimal(0));
		totalMaterialOutDetail.setSuperSetCost(new BigDecimal(0));
		totalMaterialOutDetail.setWeight(new BigDecimal(0));
		totalMaterialOutDetail.setFactoryAddMoney(new BigDecimal(0));
		totalMaterialOutDetail.setSecWeight(new BigDecimal(0));
		totalMaterialOutDetail.setSecMaterMoney(new BigDecimal(0));
		totalMaterialOutDetail.setTotalMoney(new BigDecimal(0));
		totalMaterialOutDetail.setTotalProcessCost(new BigDecimal(0));
		totalMaterialOutDetail.setTemplateFree(new BigDecimal(0));
		totalMaterialOutDetail.getMaterialOut().setBillNumber("合计");
		totalMaterialOutDetail.setStyleName("合计:");
		
		
		
		for(MaterialOutDetail materialOutDetail : list){			
			totalMaterialOutDetail.setProductAmount(
					totalMaterialOutDetail.getProductAmount() + materialOutDetail.getProductAmount());
			totalMaterialOutDetail.setProductWeight(
					materialOutDetail.getProductWeight().add(totalMaterialOutDetail.getProductWeight()));
			totalMaterialOutDetail.setGoldWeight(
					materialOutDetail.getGoldWeight().add(totalMaterialOutDetail.getGoldWeight()));
			totalMaterialOutDetail.setConsumeWeight(
					materialOutDetail.getConsumeWeight().add(totalMaterialOutDetail.getConsumeWeight()));
			totalMaterialOutDetail.setGoldMoney(
					materialOutDetail.getGoldMoney().add(totalMaterialOutDetail.getGoldMoney()));
			totalMaterialOutDetail.setTotalProcessCost(
					materialOutDetail.getTotalProcessCost().add(totalMaterialOutDetail.getTotalProcessCost()));
			totalMaterialOutDetail.setAddProcessCost(
					materialOutDetail.getAddProcessCost().add(totalMaterialOutDetail.getAddProcessCost()));
			totalMaterialOutDetail.setSuperSetCost(
					materialOutDetail.getSuperSetCost().add(totalMaterialOutDetail.getSuperSetCost()));
			totalMaterialOutDetail.setAmount(
					materialOutDetail.getAmount() + totalMaterialOutDetail.getAmount());
			totalMaterialOutDetail.setWeight(
					materialOutDetail.getWeight().add(totalMaterialOutDetail.getWeight()));
			totalMaterialOutDetail.setFactoryAddMoney(
					materialOutDetail.getFactoryAddMoney().add(totalMaterialOutDetail.getFactoryAddMoney()));
			totalMaterialOutDetail.setSecAmount(
					materialOutDetail.getSecAmount() + totalMaterialOutDetail.getSecAmount());
			totalMaterialOutDetail.setSecWeight(
					materialOutDetail.getSecWeight().add(totalMaterialOutDetail.getSecWeight()));			
			totalMaterialOutDetail.setSecMaterMoney(
					materialOutDetail.getSecMaterMoney().add(totalMaterialOutDetail.getSecMaterMoney()));
			totalMaterialOutDetail.setTotalMoney(					
					materialOutDetail.getTotalMoney().add(totalMaterialOutDetail.getTotalMoney()));
			totalMaterialOutDetail.setTemplateFree(materialOutDetail.getTemplateFree().add(totalMaterialOutDetail.getTemplateFree()));
			
		}
		list.add(totalMaterialOutDetail);
	}
	
	@RequestMapping("/totalBillList")
	public ModelAndView totalBillList(
			@Valid @ModelAttribute("queryParam") MaterialInQueryParam queryParam,
			BindingResult result, Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_TOTAL_LIST_PAGE);

		
		/*if (queryParam.getBillNumber() == null || queryParam.getBillNumber().equals("")){
			modelAndView.addObject("errorMessage", "单据编码不能为空!");
			return modelAndView;
		}
		queryParam.setBillStatus(1);
		Collection<MaterialOutDetail> produectTotalList = materialOutService.staticOutBillByProduct(queryParam);
		Collection<MaterialOutDetail> mainMaterialTotalList = materialOutService.staticOutBillByMainMaterial(queryParam);
		Collection<MaterialOutDetail> secMaterialTotalList = materialOutService.staticOutBillBySecMaterial(queryParam);
		Collection<MaterialOutDetail> feeTotalList = materialOutService.staticOutBillByTotalFee(queryParam);
		
		String buttonExport = request.getParameter("exportButton");
		
		if ("exportButton".equals(buttonExport)){			
			modelAndView.addObject("datasource", produectTotalList);
			modelAndView.addObject("mainMaterSubReportDatasource", mainMaterialTotalList);
			modelAndView.addObject("secMaterSubReportDatasource", secMaterialTotalList);
			modelAndView.addObject("feeTotalMaterSubReportDatasource", feeTotalList);
			modelAndView.addObject("format", queryParam.getExportFormat());	
			modelAndView.setViewName(VIEW_LIST_TOTAL_REPORT_PAGE);			
		}
		else{
			modelAndView.addObject("queryParam", queryParam);
			modelAndView.addObject("produectTotalList", produectTotalList);
			modelAndView.addObject("mainMaterialTotalList", mainMaterialTotalList);
			modelAndView.addObject("secMaterialTotalList", secMaterialTotalList);
			modelAndView.addObject("feeTotalList", feeTotalList);
		}*/
		return modelAndView;
	}

	@RequestMapping(value = "/doSubmit", method = RequestMethod.GET)
	public void doSubmit(@Valid String id) {
		materialOutService.submitBill(Long.valueOf(id));
		
	}

	@RequestMapping(value = "/doDelBillDetail", method = RequestMethod.GET)
	public void doDelBillDetail(@Valid String id) {
		materialOutService.deleteMaterialOutDetail(Long.valueOf(id));
	}

	@RequestMapping(value = "/doDelBill", method = RequestMethod.GET)
	public void doDelBill(@Valid String id) {
		materialOutService.deleteMaterialOut(Long.valueOf(id));

	}
}
