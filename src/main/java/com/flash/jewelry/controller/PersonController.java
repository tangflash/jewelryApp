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

import com.flash.jewelry.model.Person;
import com.flash.jewelry.service.PersonService;

@Controller
@RequestMapping("/person*")
@SessionAttributes("person")
public class PersonController {
	
	//private final static Logger logger = Logger.getLogger(MateralInfInputController.class); 
	
	private final static String Edit_VIEW_PAGE = "baseData/personEdit";
	private final static String List_VIEW_PAGE = "baseData/personList";
	
	@Autowired
	private PersonService personService = null;

	@RequestMapping("/showAddPage")
	public ModelAndView showAddPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);	
		Person person = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;		
	}

	@RequestMapping("/showListPage")
	public ModelAndView showListPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		Collection<Person> list = personService.selectPerson(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doDelete")
	public ModelAndView doDelete(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(List_VIEW_PAGE);
		personService.deletePerson(Integer.valueOf(id));
		Collection<Person> list = personService.selectPerson(null);
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/doEdit")
	public ModelAndView d(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Edit_VIEW_PAGE);		
		Person person = personService.selectPersonById(Long.valueOf(id));
		modelAndView.addObject("Person", person);
		return modelAndView;
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	@ModelAttribute("person")
	public Person createPersonBean() {
		return new Person();
	}

	@RequestMapping(value="/doSave",method = RequestMethod.POST)
	public String processSubmit(@Valid Person person, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			//return null;
			return Edit_VIEW_PAGE;			
		}
		String message = null;
		if (personService.isRepeat(person)) {
			message = "工号不能重复!";
			//redirectAttrs.addFlashAttribute("message", message);
			model.addAttribute("errorMessage", message);
			return Edit_VIEW_PAGE;
		}		
		if (person.getId() != 0)
			personService.updatePerson(person);
		else
			personService.insertPerson(person);		
			
		message = "保存成功!";
		model.addAttribute("successMessage", message);
		person = new Person();
		model.addAttribute(person);
		// Success response handling
		//redirectAttrs.addFlashAttribute("message", message);
		//return "redirect:/materalInfInput";
		return Edit_VIEW_PAGE;
	}

}
