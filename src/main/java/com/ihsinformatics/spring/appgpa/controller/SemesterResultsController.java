package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

@Controller
@RequestMapping("/semester-results")
public class SemesterResultsController {

	@Autowired
	private SemesterResultsService semesterResultsService;

	public void setSemesterResultsService(SemesterResultsService semesterResultsService) {
		this.semesterResultsService = semesterResultsService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addSemester() {
		return "";
	}
}