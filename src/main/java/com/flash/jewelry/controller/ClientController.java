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

import com.flash.jewelry.model.Client;
import com.flash.jewelry.service.ClientService;

@Controller
@RequestMapping("/client*")
@SessionAttributes("client")
public class ClientController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "baseData/clientEdit";
	private final static String List_VIEW_PAGE = "baseData/clientList";
	
	@Autowired
	private ClientService clientService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		Client client = new Client();
		modelAndView.addObject("client", client);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<Client> list = clientService.selectClient(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		clientService.deleteClient(Integer.valueOf(id));
		Collection<Client> list = clientService.selectClient(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		Client client = clientService.selectClientById(Long.valueOf(id));
		modelAndView.addObject("client", client);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("client")
	public Client createClientBean() {
		return new Client();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid Client client, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (clientService.isRepeat(client)) {
			message = "编码不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}		
		if (client.getId() != 0)
			clientService.updateClient(client);
		else
			clientService.insertClient(client);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		client = new Client();
		model.addAttribute(client);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
