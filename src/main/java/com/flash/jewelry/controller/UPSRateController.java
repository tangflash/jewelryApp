package com.flash.jewelry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UPSRateController {
	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		System.out.println("start to work now!!!");
		return new ModelAndView("welcome");
	}
}
