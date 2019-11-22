package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/result")
public class ResultController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addAStudent() {
		return "";
	}
}
