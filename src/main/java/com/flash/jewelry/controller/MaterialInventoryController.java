package com.flash.jewelry.controller;

import java.util.Collection;
import java.util.Date;

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

import com.flash.jewelry.model.ComQueryParam;
import com.flash.jewelry.model.MaterialIn;
import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.MaterialInventory;
import com.flash.jewelry.model.MaterialOut;
import com.flash.jewelry.model.MaterialOutDetail;
import com.flash.jewelry.service.InventoryManagerService;
import com.flash.jewelry.service.MaterialManagerService;
import com.flash.jewelry.service.MaterialOutService;

@Controller
@RequestMapping("/materialInventory")
public class MaterialInventoryController {
	
	private final static String VIEW_LIST_PAGE = "materialInventoryList";
	
	@Autowired
	private InventoryManagerService inventoryManagerService;
	
	
	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		ComQueryParam comQueryParam = new ComQueryParam();
		modelAndView.setViewName(VIEW_LIST_PAGE);		
		modelAndView.addObject("comQueryParam", comQueryParam);		

		return modelAndView;
	}
	
	@RequestMapping("/findList")
	public ModelAndView findListPage(@Valid @ModelAttribute("comQueryParam") ComQueryParam comQueryParam
			, BindingResult result,Model model, RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName(VIEW_LIST_PAGE);
		
		if (result.hasErrors()){
			modelAndView.addObject("comQueryParam", comQueryParam);
			return modelAndView;
		}
		
		if ((comQueryParam.getMaterNum() == null) || (comQueryParam.getMaterNum().equals("")))
			comQueryParam.setMaterNum(null);			
		Collection<MaterialInventory> list = inventoryManagerService.findMaterialInventory(comQueryParam);		
		modelAndView.addObject("comQueryParam", comQueryParam);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
}
