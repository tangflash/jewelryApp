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

import com.flash.jewelry.model.Product;
import com.flash.jewelry.model.ProductStyle;
import com.flash.jewelry.service.ProductService;
import com.flash.jewelry.service.ProductStyleService;

@Controller
@RequestMapping("/productStyle*")
@SessionAttributes("productStyle")
public class ProductStyleController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "baseData/productStyleEdit";
	private final static String List_VIEW_PAGE = "baseData/productStyleList";
	
	@Autowired
	private ProductStyleService productStyleService = null;
	@Autowired
	private ProductService productService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		ProductStyle productStyle = new ProductStyle();
		modelAndView.addObject("productStyle", productStyle);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<ProductStyle> list = productStyleService.selectProductStyle(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		productStyleService.deleteProductStyle(Integer.valueOf(id));
		Collection<ProductStyle> list = productStyleService.selectProductStyle(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		ProductStyle productStyle = productStyleService.selectProductStyleById(Long.valueOf(id));
		modelAndView.addObject("productStyle", productStyle);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("productStyle")
	public ProductStyle createProductStyleBean() {
		return new ProductStyle();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid ProductStyle productStyle, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (productStyleService.isRepeatByNum(productStyle)) {
			message = "编码不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		
		if (productStyleService.isRepeatByName(productStyle)) {
			message = "名称不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		
		if (productStyle.getProduct().getName() == null){
			message = "所属品名不能为空!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		Product product = productService.selectProductByNumberOrName(productStyle.getProduct().getName());
		if (product == null){
			message = "所属品名[" +productStyle.getProduct().getName()+ "]不存在!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		productStyle.setProduct(product);
		if (productStyle.getId() != 0)
			productStyleService.updateProductStyle(productStyle);
		else
			productStyleService.insertProductStyle(productStyle);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		productStyle = new ProductStyle();
		model.addAttribute(productStyle);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
