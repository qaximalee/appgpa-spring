package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.imp.CourseServiceImp;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	public void setCourseService(CourseServiceImp courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addAStudent() {
		return "";
	}
}
