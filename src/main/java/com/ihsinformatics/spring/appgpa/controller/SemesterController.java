package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.imp.SemesterServiceImp;

@Controller
@RequestMapping("/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	public void setSemesterService(SemesterServiceImp semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/add_semesters", method = RequestMethod.GET)
	public String addSemester() {
		return "";
	}
}