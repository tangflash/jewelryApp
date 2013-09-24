package com.flash.jewelry.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.flash.jewelry.common.StrConstant;
import com.flash.jewelry.common.StringUtil;
import com.flash.jewelry.model.BalanceBill;
import com.flash.jewelry.model.BalanceBillQueryParam;
import com.flash.jewelry.model.BalanceMainMaterDetail;
import com.flash.jewelry.model.Client;
import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.BalanceBillService;
import com.flash.jewelry.service.BaseDataService;
import com.flash.jewelry.service.ClientService;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialManagerService;
import com.flash.jewelry.service.MaterialOutService;

@Controller
@RequestMapping("/balanceBill*")
public class BalanceBillController {

	private final static String VIEW_EDIT_PAGE = "balanceBillEdit";
	private final static String VIEW_LIST_PAGE = "balanceBillList";
	private final static String VIEW_LIST_REPORT_PAGE = "materialOutBillReport";
	private final static String VIEW_LIST_TOTAL_REPORT_PAGE = "materialOutBillTotalReport";
	private final static String VIEW_TOTAL_LIST_PAGE = "materialOutTotalBillList";
	private final static String BALANCE_BILL_KEY = "balanceBill";

	@Autowired
	private BalanceBillService balanceBillService;
	@Autowired
	private MaterialManagerService materialManagerService;
	@Autowired
	private MaterialOutService materialOutService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private BaseDataService baseDataService;

	

	@RequestMapping("/showPage")
	public ModelAndView showPage(String id) {
		ModelAndView modelAndView = getShowPageModelAndView(id, false, "");	

		return modelAndView;
	}

	private ModelAndView getShowPageModelAndView(String id, boolean isExport, String exportFormat) {
		ModelAndView modelAndView = new ModelAndView();
		
		BalanceBill balanceBill = null;
		if (id == null || id.equals("")) {
			balanceBill = new BalanceBill();
			balanceBill.setId(0);
			// SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");
			// dateFormat.f
			balanceBill.setBizDate(new Date());
			balanceBill.setCreateTime(new Date());	
			
			modelAndView.setViewName(VIEW_EDIT_PAGE);
			modelAndView.addObject("balanceBill", balanceBill);
			return modelAndView;
		}
		else{
			balanceBill = balanceBillService.selectById(id);	
			Collection<BalanceMainMaterDetail> balanceMainMaterDetailList = balanceBillService.selectByBillId(id);
			sumBalanceMainMaterDetail(balanceMainMaterDetailList);
			//materialOut = materialOutService.selectMaterialOutById("" + materialOutDetail.getBillId());
			Collection<MaterialOut> materialOutList = materialOutService.selectByBalanceBillId(Long.valueOf(id));
			//materialOut.setMaterialOutDetail(materialOutDetail);
			Collection<MaterialOutDetail> produectTotalList = new ArrayList<MaterialOutDetail>();
			//Collection<MaterialOutDetail> mainMaterialTotalList = new ArrayList<MaterialOutDetail>();	
			Collection<MaterialOutDetail> secMaterialTotalList = new ArrayList<MaterialOutDetail>();
			Collection<BalanceBill> feeTotalList = null;
			
			Map feeInforMap = null;
			for (MaterialOut materialOut : materialOutList) {
				Collection<MaterialOutDetail> tempProduectTotalList = staticOutBillByProduct(materialOut.getId());
				sumMaterialOutDetail(tempProduectTotalList);
				produectTotalList.addAll(tempProduectTotalList);
				
				staticOutBillByMainMaterial(balanceMainMaterDetailList,materialOut.getId());
			
				
				Collection<MaterialOutDetail> tempSecMaterialTotalList = materialOutService.staticOutBillBySecMaterial(materialOut.getId());				
				secMaterialTotalList.addAll(tempSecMaterialTotalList);
				
				feeInforMap = staticOutBillByTotalFee(feeInforMap, materialOut.getId());											
			}
			
			sumSecMaterial(secMaterialTotalList);
			feeTotalList = ConvertFeeMapInforToList(feeInforMap);
			
			if (isExport){
				modelAndView.addObject("datasource", produectTotalList);
				modelAndView.addObject("mainMaterSubReportDatasource", balanceMainMaterDetailList);
				modelAndView.addObject("secMaterSubReportDatasource", secMaterialTotalList);
				modelAndView.addObject("feeTotalMaterSubReportDatasource", feeTotalList);
				modelAndView.addObject("format", exportFormat);	
				modelAndView.addObject("factoryName",baseDataService.getFactoryName());
				modelAndView.addObject("printTime", new Date());
				modelAndView.addObject("makeBillPerson", baseDataService.getMakeBillPerson());
				modelAndView.setViewName(VIEW_LIST_TOTAL_REPORT_PAGE);	
				return modelAndView;
			}
			else{
				modelAndView.addObject("produectTotalList", produectTotalList);
				modelAndView.addObject("mainMaterialTotalList", balanceMainMaterDetailList);
				modelAndView.addObject("secMaterialTotalList", secMaterialTotalList);
				modelAndView.addObject("feeTotalList", feeTotalList);
				modelAndView.setViewName(VIEW_EDIT_PAGE);
				modelAndView.addObject("balanceBill", balanceBill);
				//modelAndView.addObject("exportFormat", "pdf");	
				return modelAndView;
			}
		}
	}

	private void sumSecMaterial(
			Collection<MaterialOutDetail> secMaterialTotalList) {
		MaterialOutDetail materialOutDetailTotal = new MaterialOutDetail();
		MaterialOut materialOut = new MaterialOut();
		materialOut.setGoldTypeName("合计:");
		materialOutDetailTotal.setMaterialOut(materialOut);
		//materialOutDetailTotal.setSecPrice(new BigDecimal(0));
		materialOutDetailTotal.setSecAmount(0);
		materialOutDetailTotal.setSecWeight(new BigDecimal(0));
		materialOutDetailTotal.setSecMaterMoney(new BigDecimal(0));
		
		for (MaterialOutDetail item : secMaterialTotalList) {
			materialOutDetailTotal.setSecAmount(materialOutDetailTotal.getSecAmount() + item.getSecAmount());
			materialOutDetailTotal.setSecWeight(materialOutDetailTotal.getSecWeight().add(item.getSecWeight()));
			materialOutDetailTotal.setSecMaterMoney(materialOutDetailTotal.getSecMaterMoney().add(item.getSecMaterMoney()));
		}
		secMaterialTotalList.add(materialOutDetailTotal);
	}

	private void sumBalanceMainMaterDetail(
			Collection<BalanceMainMaterDetail> balanceMainMaterDetailList) {
		BalanceMainMaterDetail balanceMainMaterDetailTotal = new BalanceMainMaterDetail();
		balanceMainMaterDetailTotal.setMaterId(-1);
		balanceMainMaterDetailTotal.setCurAmount(0);
		balanceMainMaterDetailTotal.setCurWeight(new BigDecimal(0));
		balanceMainMaterDetailTotal.setPriorAmount(0);
		balanceMainMaterDetailTotal.setPriorWeight(new BigDecimal(0));
		balanceMainMaterDetailTotal.setInAmount(0);
		balanceMainMaterDetailTotal.setInWeight(new BigDecimal(0));
		balanceMainMaterDetailTotal.setMaterName("合计:");
		
		for (BalanceMainMaterDetail item : balanceMainMaterDetailList) {
			balanceMainMaterDetailTotal.setCurAmount(balanceMainMaterDetailTotal.getCurAmount() + item.getCurAmount());
			balanceMainMaterDetailTotal.setCurWeight(balanceMainMaterDetailTotal.getCurWeight().add(item.getCurWeight()));
			
			balanceMainMaterDetailTotal.setPriorAmount(balanceMainMaterDetailTotal.getPriorAmount() + item.getPriorAmount());
			balanceMainMaterDetailTotal.setPriorWeight(balanceMainMaterDetailTotal.getPriorWeight().add(item.getPriorWeight()));
			
			balanceMainMaterDetailTotal.setInAmount(balanceMainMaterDetailTotal.getInAmount() + item.getInAmount());
			balanceMainMaterDetailTotal.setInWeight(balanceMainMaterDetailTotal.getInWeight().add(item.getInWeight()));
		}
		balanceMainMaterDetailList.add(balanceMainMaterDetailTotal);
	}

	private Collection<BalanceBill> ConvertFeeMapInforToList(Map feeInforMap) {
		Collection<BalanceBill> balanceBillList = new ArrayList();
		BalanceBill balanceBillTotal = null;
		//合计		
		balanceBillTotal = new BalanceBill();
		balanceBillTotal.setFeeTotalInfor(String.format("合计: %s元", feeInforMap.get("Total")));		
		feeInforMap.remove("Total");
		
		BalanceBill balanceBill = null;
		Collection<String> feeInfoList = feeInforMap.values();		
		for (String feeInfor : feeInfoList) {
			balanceBill = new BalanceBill();
			balanceBill.setFeeTotalInfor(feeInfor);
			balanceBillList.add(balanceBill);
		};
		balanceBillList.add(balanceBillTotal);
		return balanceBillList;
	}

	
	
	private Map<String, String> staticOutBillByTotalFee(Map<String, String> feeInforMap, long id) {
		if (feeInforMap == null)
			feeInforMap = new HashMap<String, String>();		
		if (feeInforMap.size() < 1){
			feeInforMap.put("SuperSetCost", "");
			feeInforMap.put("TotalProcessCost", "");
			feeInforMap.put("GoldMoney", "");
			feeInforMap.put("SecMaterMoney", "");
			feeInforMap.put("AddProcessCost", "");
			feeInforMap.put("Total", "0");
		}
		
		BalanceMainMaterDetail result = new BalanceMainMaterDetail();
		Collection<MaterialOutDetail> materialOutDetailList = materialOutService.staticOutBillByTotalFee(id);
		String feeString = null;
		String itemName = null;
		int feeStrLen = 8;
		String addedStr = "__";
		BigDecimal feeTotal = new BigDecimal(0);
		for (MaterialOutDetail materialOutDetail : materialOutDetailList) {		
			feeTotal = feeTotal.add(materialOutDetail.getSuperSetCost());
			feeString = StringUtil.makeAddedStr(String.format("%.0f", materialOutDetail.getSuperSetCost()), feeStrLen, null);
			itemName = StringUtil.makeAddedStr(String.format("%s超镶工费", materialOutDetail.getMaterialOut().getGoldTypeName()), -feeStrLen,addedStr);
			feeInforMap.put("SuperSetCost", String.format("%s  %s", 
					feeInforMap.get("SuperSetCost"), itemName
					) + feeString);
			//feeInforMap.put("SuperSetCost", feeString);
			
			feeTotal = feeTotal.add(materialOutDetail.getTotalProcessCost());
			feeString = StringUtil.makeAddedStr(String.format("%.0f", materialOutDetail.getTotalProcessCost()), feeStrLen, null);
			itemName = StringUtil.makeAddedStr(String.format("%s工费", materialOutDetail.getMaterialOut().getGoldTypeName()), -feeStrLen, addedStr);
			feeInforMap.put("TotalProcessCost", String.format("%s  %s", 
					feeInforMap.get("TotalProcessCost"), itemName) + feeString);
			
			feeTotal = feeTotal.add(materialOutDetail.getGoldMoney());
			feeString = StringUtil.makeAddedStr(String.format("%.0f", materialOutDetail.getGoldMoney()), feeStrLen, null);
			itemName = StringUtil.makeAddedStr(String.format("%s金料额", materialOutDetail.getMaterialOut().getGoldTypeName()), -feeStrLen, addedStr);
			feeInforMap.put("GoldMoney", String.format("%s  %s", 
					feeInforMap.get("GoldMoney"), itemName) + feeString);
			
			feeTotal = feeTotal.add(materialOutDetail.getSecMaterMoney());
			feeString = StringUtil.makeAddedStr(String.format("%.0f", materialOutDetail.getSecMaterMoney()), feeStrLen, null);
			itemName = StringUtil.makeAddedStr(String.format("%s副石额", materialOutDetail.getMaterialOut().getGoldTypeName()), -feeStrLen, addedStr);
			feeInforMap.put("SecMaterMoney", String.format("%s  %s", 
					feeInforMap.get("SecMaterMoney"), itemName) + feeString);
			
			feeTotal = feeTotal.add(materialOutDetail.getAddProcessCost());
			feeString = StringUtil.makeAddedStr(String.format("%.0f", materialOutDetail.getAddProcessCost()), feeStrLen, null);
			itemName = StringUtil.makeAddedStr(String.format("%s附加工费", materialOutDetail.getMaterialOut().getGoldTypeName()), -feeStrLen, addedStr);
			feeInforMap.put("AddProcessCost", String.format("%s  %s", 
					feeInforMap.get("AddProcessCost"), itemName) + feeString);			
			
		}
		String feeTotalStr = feeInforMap.get("Total");
		feeTotal = feeTotal.add(new BigDecimal(feeTotalStr));
		feeInforMap.put("Total", String.format("%.0f", feeTotal));
		return feeInforMap;
	}
	

	private void staticOutBillByMainMaterial(Collection<BalanceMainMaterDetail> balanceMainMaterDetailList,
			long materialOutBillId) {
		Collection<MaterialOutDetail> tempList = materialOutService.staticOutBillByMainMaterial(materialOutBillId);
		int totalAmount = 0;
		BigDecimal  totalWeight = new BigDecimal(0);
		for (MaterialOutDetail materialOutDetail : tempList) {
			totalAmount = totalAmount + materialOutDetail.getAmount();
			totalWeight = totalWeight.add(materialOutDetail.getWeight());
			for (BalanceMainMaterDetail balanceMainMaterDetail : balanceMainMaterDetailList) {
				if (balanceMainMaterDetail.getMaterId() == materialOutDetail.getMaterId()){
					balanceMainMaterDetail.setMaterUsedInfor(String.format("%s  %s: 数量: %d,重量: %.3f", 
							balanceMainMaterDetail.getMaterUsedInfor() == null ? "" : balanceMainMaterDetail.getMaterUsedInfor(),
									materialOutDetail.getMaterialOut().getGoldTypeName(), 
							 materialOutDetail.getAmount(), materialOutDetail.getWeight()));
					break;
				}
			}
		}
		
		//汇总
		if (totalAmount > 0){
			for (MaterialOutDetail materialOutDetail : tempList) {
				for (BalanceMainMaterDetail balanceMainMaterDetail : balanceMainMaterDetailList) {
					if (balanceMainMaterDetail.getMaterId() == -1){
						balanceMainMaterDetail.setMaterUsedInfor(String.format("%s  %s汇总: 数量: %d,重量: %.3f",
								balanceMainMaterDetail.getMaterUsedInfor() == null ? "" : balanceMainMaterDetail.getMaterUsedInfor(),
										materialOutDetail.getMaterialOut().getGoldTypeName(), 
										totalAmount, totalWeight));
						break;
					}
				}
				break;
			}
		}
	}		

	

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(VIEW_LIST_PAGE);
		BalanceBillQueryParam queryParam = new BalanceBillQueryParam();
		modelAndView.addObject("queryParam", queryParam);

		return modelAndView;
	}
	
	

	@RequestMapping("/findList")
	public ModelAndView findListPage(
			@Valid @ModelAttribute("queryParam") BalanceBillQueryParam queryParam,
			BindingResult result, Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_LIST_PAGE);

		if (result.hasErrors()) {
			modelAndView.addObject("queryParam", queryParam);
			return modelAndView;
		}		
			
		Collection<BalanceBill> list = balanceBillService.find(queryParam);		
		modelAndView.addObject("queryParam", queryParam);
		modelAndView.addObject("list", list);		
		return modelAndView;
	}
	
	private void sumMaterialOutDetail(Collection<MaterialOutDetail> list){
		MaterialOutDetail totalMaterialOutDetail = new MaterialOutDetail();
		MaterialOut materialOut = new MaterialOut();
		materialOut.getBillStatus().setNumber("-1");
		materialOut.getBillStatus().setName("");
		materialOut.setCreateTime(null);
		totalMaterialOutDetail.setMaterialOut(materialOut);
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
		//totalMaterialOutDetail.getMaterialOut().setBillNumber("合计");
		totalMaterialOutDetail.setGroupName("合计:");
		
		
		
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
			totalMaterialOutDetail.setProcessCost(
					materialOutDetail.getProcessCost().add(totalMaterialOutDetail.getProcessCost()));
			totalMaterialOutDetail.setAddProcessCost(
					materialOutDetail.getAddProcessCost().add(totalMaterialOutDetail.getAddProcessCost()));
			totalMaterialOutDetail.setSuperSetCost(
					materialOutDetail.getSuperSetCost().add(totalMaterialOutDetail.getSuperSetCost()));
					
			totalMaterialOutDetail.setTotalProcessCost(
					materialOutDetail.getTotalProcessCost().add(totalMaterialOutDetail.getTotalProcessCost()));
			totalMaterialOutDetail.setTotalMoney(					
					materialOutDetail.getTotalMoney().add(totalMaterialOutDetail.getTotalMoney()));
			
		}
		list.add(totalMaterialOutDetail);
	}
	
	private Collection<MaterialOutDetail> staticOutBillByProduct(long materialOutBillId){
		Collection<MaterialOutDetail> resultList = materialOutService.staticOutBillByProduct(materialOutBillId);		
		//MaterialOutDetail materialOutDetail = new MaterialOutDetail();
		if (resultList.size() > 0){			
			for (MaterialOutDetail item : resultList) {
				item.setGroupName("成色:" + item.getMaterialOut().getGoldTypeName() + " 金价/克:" + String.format("%.2f", item.getMaterialOut().getGoldPrice()));				
				break;
			}						
		}		
		return resultList;
	}
	
	@RequestMapping("/makeNewTotalBill")
	public ModelAndView makeNewTotalBill(
			@Valid @ModelAttribute(BALANCE_BILL_KEY) BalanceBill balanceBill, String exportButton,
			BindingResult result, Model model,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_EDIT_PAGE);
		
		modelAndView.addObject(BALANCE_BILL_KEY, balanceBill);
		
		if (result.hasErrors()) {			
			return modelAndView;
		}
		
		//导出
		if ("exportButton".equals(exportButton)){
			modelAndView = getShowPageModelAndView("" + balanceBill.getId(), true, balanceBill.getExportFormat());

			return modelAndView;	
		}
		
		Client client = clientService.getClientByNumOrName(balanceBill.getClientName());
		if (client == null || client.getId() == 0){
			modelAndView.addObject(StrConstant.ERROR_MESSAGE_KEY, "客户编码/名称:" + balanceBill.getClientName() + "不存在!");
			return modelAndView;
		}
		balanceBill.setClientId(client.getId());
		balanceBill.setClientName(client.getName());
		
		boolean isExistNoSubmitBill = balanceBillService.isExistNoSubmitBill(balanceBill.getClientId());
		if (isExistNoSubmitBill){			
			modelAndView.addObject(StrConstant.ERROR_MESSAGE_KEY, "此客户有未提交的结算单，不能生成 新的结算单。请先处理存在的结算单。");
			return modelAndView;
		}
		
		Collection<MaterialOut>  materialOutList = materialOutService.selectNoBalanceBill(balanceBill.getClientId());
		if (materialOutList.size() < 1){			
			modelAndView.addObject(StrConstant.ERROR_MESSAGE_KEY, "此客户没有可结算的出货单");
			return modelAndView;
		}
		
		balanceBill.setCreateTime(new Date());
		balanceBillService.insert(balanceBill);
		
		List materialOutBillIdList = getMaterialOutBillIds(materialOutList);
		materialOutService.updateBalanceId(balanceBill.getId(), materialOutBillIdList);		
		
		balanceBillService.iniMainMaterDetail(balanceBill.getId(), balanceBill.getClientId());
		
		modelAndView.addObject(BALANCE_BILL_KEY, balanceBill);
		modelAndView = getShowPageModelAndView("" + balanceBill.getId(), false , "");	

		return modelAndView;		
	}

	private List getMaterialOutBillIds(Collection<MaterialOut> materialOutList) {	
		List result = new ArrayList();
		//if (materialOutList.size() < 1) return "";		
		for (MaterialOut materialOut : materialOutList) {
			result.add(materialOut.getId());
		}
		return result;
	}

	@RequestMapping(value = "/doSubmit", method = RequestMethod.GET)
	public void doSubmit(@Valid String id) {
		balanceBillService.submitBill(Long.valueOf(id));
		
	}
	

	@RequestMapping(value = "/doDelBill", method = RequestMethod.GET)
	public void doDelBill(@Valid String id) {
		balanceBillService.delete(Long.valueOf(id));

	}
	
	public static void main(String[] args){
		MaterialOutDetail materialOutDetail = new MaterialOutDetail();
		MaterialOut  materialOut = new  MaterialOut();
		materialOutDetail.setMaterialOut(materialOut);
		materialOutDetail.getMaterialOut().setGoldTypeName("18K");
		materialOutDetail.setSuperSetCost(new BigDecimal(10.2311));
		String sssString = String.format("%s %s超镶工费  %25.2f", 
				"", materialOutDetail.getMaterialOut().getGoldTypeName(),
				materialOutDetail.getSuperSetCost());
		System.out.println(sssString);
		double pi = Math.PI;
		System.out.format("%s %s超镶工费  %20.2f%n", 
				"", materialOutDetail.getMaterialOut().getGoldTypeName(),
				pi);
		System.out.format("%20.3f%n", pi);   // -->  "     3.142"
		
		System.out.println(String.format("%1$10s", "aaaa")); 
	}
}
