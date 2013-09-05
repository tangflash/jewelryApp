package com.flash.jewelry.controller;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.flash.jewelry.model.Client;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInDetail;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.service.ClientService;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialInService;
import com.flash.jewelry.service.MaterialManagerService;

@Controller
@RequestMapping("/materialInBill*")
public class MaterialInController {

	private final static String VIEW_EDIT_PAGE = "materialInBillEdit";
	private final static String VIEW_LIST_PAGE = "materialInBillList";

	@Autowired
	private MaterialInService materialInService;
	@Autowired
	private MaterialManagerService materialManagerService;
	@Autowired
	private InventoryManagerService inventoryManagerService;
	@Autowired
	private ClientService clientService;

	@RequestMapping("/showPage")
	public ModelAndView showPage(String id) {
		ModelAndView modelAndView = new ModelAndView();
		MaterialIn materialIn = null;
		if (id == null || id.equals("")){
			materialIn = new MaterialIn();
			materialIn.setId(0);
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");
			//dateFormat.f
			materialIn.setBizDate(new Date());
			materialIn.setCreateTime(new Date());
			MaterialInDetail materialInDetail = new MaterialInDetail();
			materialIn.setMaterialInDetail(materialInDetail);
		}
		else{
			MaterialInDetail materialInDetail = materialInService.selectMaterialInDetailById(id);		
			materialIn = materialInService.selectMaterialInById("" + materialInDetail.getBillId());		
			materialIn.setMaterialInDetail(materialInDetail);			
		}
		modelAndView.setViewName(VIEW_EDIT_PAGE);
		modelAndView.addObject("materialIn", materialIn);
		// modelAndView.addObject("materialInDetail", materialInDetail);

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
	
	@RequestMapping("/findList")
	public ModelAndView findListPage(@Valid @ModelAttribute("queryParam") MaterialInQueryParam queryParam
			, BindingResult result,Model model, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName(VIEW_LIST_PAGE);
		
		if (result.hasErrors()){
			modelAndView.addObject("queryParam", queryParam);
			return modelAndView;
		}
		
		Collection<MaterialIn> list = materialInService.findMateriallIn(queryParam);		
		modelAndView.addObject("queryParam", queryParam);
		modelAndView.addObject("list", list);
		return modelAndView;
	}	
	
	
	

	@RequestMapping(value = "/doSaveBillHead", method = RequestMethod.POST)
	public ModelAndView doSaveBillHead(@Valid @ModelAttribute("materialIn") MaterialIn materialIn
			, BindingResult result,Model model, RedirectAttributes redirectAttrs) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(VIEW_EDIT_PAGE);
		
		if (result.hasErrors()){
			modelAndView.addObject("materialIn", materialIn);
			return modelAndView;
		}
		if (materialIn.getId() > 0) {
			if (materialInService.isBillNumRepeat(materialIn.getBillNumber(),
					materialIn.getId())) {
				modelAndView.addObject("errorMessage", "单据编码不能重复!");				
				return modelAndView;
			}
		} else if (materialInService.isBillNumRepeat(
				materialIn.getBillNumber(), 0)) {
			modelAndView.addObject("errorMessage", "单据编码不能重复!");			
			return modelAndView;
		}
		
		Client client = clientService.getClientByNumOrName(materialIn.getClientName());
		if (client == null) {			
			modelAndView.addObject("errorMessage", "客户编码/名称" + materialIn.getClientName() + "不存在!");				
			return modelAndView;
		}
		materialIn.setClientId(client.getId());

		if (materialIn.getId() > 0) {
			materialInService.updateMaterialIn(materialIn);
		} else {
			materialInService.insertMaterialIn(materialIn);
		}

		MaterialInDetail materialInDetail = materialIn.getMaterialInDetail();
		materialInDetail.setBillId(materialIn.getId());
		
		if (materialManagerService.selectMaterialByNum(materialInDetail.getMaterNum()) == null){
			modelAndView.addObject("errorMessage", "钻石编码"+materialInDetail.getMaterNum()+"不存在!");			
			return modelAndView;
		}
		materialInService.setMaterialId(materialInDetail);
		if (materialInDetail.getId() > 0) {
			if (materialInService.isBillMaterialRepeat(
					materialInDetail.getMaterId(),
					materialInDetail.getBillId(), materialInDetail.getId())) {
				modelAndView.addObject("errorMessage", "单据中的钻石信息记录不能重复!");
				return modelAndView;
			}
		} else if (materialInService.isBillMaterialRepeat(
				materialInDetail.getMaterId(), materialInDetail.getBillId(), 0)) {
			modelAndView.addObject("errorMessage", "单据中的钻石信息记录不能重复!");			
			return modelAndView;
		}		
		
		
		if (materialInDetail.getId() > 0) {
			materialInService.updateMaterialInDetail(materialInDetail);
		} else {
			materialInService.insertMaterialInDetail(materialInDetail);
		}

		modelAndView.addObject("successMessage", "保存单据成功!");
		materialIn.setMaterialInDetail(new MaterialInDetail());
		
		modelAndView.addObject("materialIn", materialIn);
		return modelAndView;
	}
	
	@RequestMapping(value="/doSubmit", method=RequestMethod.GET)	
	public void doSubmit(@Valid String id) {
		materialInService.submitBill(Long.valueOf(id));
		inventoryManagerService.submitMaterialIn(Long.valueOf(id));		
	}
	
	@RequestMapping("/doEditBillDetail")
	public String doEditBillDetail(@Valid String id) {
		/*ModelAndView modelAndView = new ModelAndView();		
				
		MaterialInDetail materialInDetail = materialInService.selectMaterialInDetailById(id);		
		MaterialIn materialIn = materialInService.selectMaterialInById("" + materialInDetail.getBillId());		
		materialIn.setMaterialInDetail(materialInDetail);

		modelAndView.setView(new RedirectView(VIEW_EDIT_PAGE));
		modelAndView.addObject("materialIn", materialIn);	*/	

		return "redirect:/materialInBillEdit/showPage";
	}
	
	@RequestMapping(value="/doDelBillDetail", method=RequestMethod.GET)	
	public void doDelBillDetail(@Valid String id) {
		materialInService.deleteMaterialInDetail(Long.valueOf(id));	
	}
	
	@RequestMapping(value="/doDelBill", method=RequestMethod.GET)	
	public void doDelBill(@Valid String id) {
		materialInService.deleteMaterialIn(Long.valueOf(id));
				
	}
}
