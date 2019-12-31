package com.ihsinformatics.spring.appgpa.controller.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.service.ResultsService;

@RestController
@RequestMapping("/rest-result")
public class ResultRestController {

	@Autowired
	ResultsService resultsService;

	/**
	 * This method will generate Student's Overall Results by there ID. It will
	 * generate a json with NULL string if the student doesn't have any previous
	 * results.
	 * 
	 * @param studentId
	 */
	@RequestMapping(value = "/getResultByStudent/{id}", method = RequestMethod.GET)
	public void getResultByStudent(HttpServletResponse response, @PathVariable("id") int studentId) {

		List<Result> results = resultsService.generateResult(studentId);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("results", results);

		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
