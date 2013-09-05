package com.flash.jewelry.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flash.jewelry.model.GoldType;
import com.flash.jewelry.service.GoldTypeService;

@Controller
@RequestMapping("/goldType*")
@SessionAttributes("goldType")
public class GoldTypeController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "baseData/goldTypeEdit";
	private final static String List_VIEW_PAGE = "baseData/goldTypeList";
	
	@Autowired
	private GoldTypeService goldTypeService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		GoldType goldType = new GoldType();
		modelAndView.addObject("goldType", goldType);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<GoldType> list = goldTypeService.selectGoldType(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		goldTypeService.deleteGoldType(Integer.valueOf(id));
		Collection<GoldType> list = goldTypeService.selectGoldType(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		GoldType goldType = goldTypeService.selectGoldTypeById(Long.valueOf(id));
		modelAndView.addObject("goldType", goldType);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("goldType")
	public GoldType createGoldTypeBean() {
		return new GoldType();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid GoldType goldType, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (goldTypeService.isRepeatByNum(goldType)) {
			message = "编码不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		if (goldTypeService.isRepeatByName(goldType)) {
			message = "名称不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		if (goldType.getId() != 0)
			goldTypeService.updateGoldType(goldType);
		else
			goldTypeService.insertGoldType(goldType);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		goldType = new GoldType();
		model.addAttribute(goldType);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
