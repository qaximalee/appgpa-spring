package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.imp.CourseResultsServiceImp;

@Controller
@RequestMapping("/course-results")
public class CourseResultsController {

	@Autowired
	private CourseResultsService courseResultsService;

	public void setCourseResultsService(CourseResultsServiceImp courseResultsService) {
		this.courseResultsService = courseResultsService;
	}

	@RequestMapping(value = "/courseResults", method = RequestMethod.GET)
	public String addAStudent() {
		return "";
	}
}
