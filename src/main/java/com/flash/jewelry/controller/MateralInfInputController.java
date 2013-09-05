package com.flash.jewelry.controller;

import java.util.Collection;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flash.jewelry.dao.CityMapper;
import com.flash.jewelry.dao.MaterialMapper;
import com.flash.jewelry.model.Material;
import com.flash.jewelry.service.MaterialManagerService;

@Controller
@RequestMapping("/materalInf*")
@SessionAttributes("material")
public class MateralInfInputController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "materalInfInput";
	private final static String List_VIEW_PAGE = "materalInfList";
	
	@Autowired
	private MaterialManagerService materialManagerService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		Material material = new Material();
		modelAndView.addObject("material", material);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<Material> list = materialManagerService.selectMaterial(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		materialManagerService.deleteMaterial(Integer.valueOf(id));
		Collection<Material> list = materialManagerService.selectMaterial(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		Material material = materialManagerService.selectMaterialById(Long.valueOf(id));
		modelAndView.addObject("material", material);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("material")
	public Material createMaterialBean() {
		return new Material();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid Material material, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (materialManagerService.isRepeatByNum(material)) {
			message = "编码不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		if (materialManagerService.isRepeatByName(material)) {
			message = "名称不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		if (material.getId() != 0)
			materialManagerService.updateMaterial(material);
		else
			materialManagerService.insertMaterial(material);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		material = new Material();
		model.addAttribute(material);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
