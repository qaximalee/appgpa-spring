package com.ihsinformatics.spring.appgpa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.service.ResultsService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@Controller
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ResultsService resultsService;

	/**
	 * This method will show the Web view of the Result Generation page.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewResultsForm() {
		return new ModelAndView("/overall_results/generate_result").addObject("studentList",
				studentService.getAllStudents());
	}

	/**
	 * This method will get generated result of a student and parse into JSON.
	 * 
	 * @param HttpServletResponse
	 * @param studentId
	 */
	@RequestMapping(value = "/getResultByStudent", method = RequestMethod.GET)
	public void getResultByStudent(HttpServletResponse response, @RequestParam("id") int studentId) {

		List<Result> results = resultsService.generateResult(studentId);

		// This string will identify where the json results has data or not.
		if (results == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("results", new ArrayList<>());

			try {
				response.getWriter().print(jsonObject.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("------------============= RESULTSERVLET JSON ERROR =============---------------");
				e.printStackTrace();
			}
			return;
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("results", results);

		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("------------============= RESULTSERVLET JSON ERROR =============---------------");
			e.printStackTrace();
		}
	}

	/*
	 * @RequestMapping(value = "/getResultByStudent", method = RequestMethod.GET)
	 * public ModelAndView getResultByStudent(@RequestParam("id") int studentId) {
	 * return new
	 * ModelAndView("overall_results/generate_result").addObject("results",
	 * resultsService.generateResult(studentId)); }
	 */
}
