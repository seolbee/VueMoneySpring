package com.credit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index.html";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String error() {
		return "redirect:/";
	}
	
//	@RequestMapping(path="/", method=RequestMethod.GET)
}
