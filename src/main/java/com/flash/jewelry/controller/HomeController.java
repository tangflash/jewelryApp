package com.flash.jewelry.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

	private final static String VIEW_PAGE = "home";

	@RequestMapping("/")
	public String showAddPage(){
		return VIEW_PAGE;
	}

}
