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
import com.flash.jewelry.service.ProductService;

@Controller
@RequestMapping("/product*")
@SessionAttributes("product")
public class ProductController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "baseData/productEdit";
	private final static String List_VIEW_PAGE = "baseData/productList";
	
	@Autowired
	private ProductService productService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		Product product = new Product();
		modelAndView.addObject("product", product);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<Product> list = productService.selectProduct(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		productService.deleteProduct(Integer.valueOf(id));
		Collection<Product> list = productService.selectProduct(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		Product product = productService.selectProductById(Long.valueOf(id));
		modelAndView.addObject("product", product);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("product")
	public Product createProductBean() {
		return new Product();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid Product product, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (productService.isRepeatByNum(product)) {
			message = "编码不能重复!";
			//modelAndView.addObject("product", product);
			//model.addAttribute("product", product);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}	
		if (productService.isRepeatByName(product)) {
			message = "品名不能重复!";
			//modelAndView.addObject("product", product);
			//model.addAttribute("product", product);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}
		if (product.getId() != 0)
			productService.updateProduct(product);
		else
			productService.insertProduct(product);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		product = new Product();
		model.addAttribute(product);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
