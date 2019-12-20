package com.ihsinformatics.spring.appgpa.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
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

	/*
	 * @PostMapping("/getResultByStudent/{id}") public ResponseEntity<JSONObject>
	 * getResultByStudent(@PathVariable("id") int studentId) {
	 * 
	 * List<Result> results = resultsService.generateResult(studentId);
	 * 
	 * if (results == null) { JSONObject jsonObject = new JSONObject();
	 * jsonObject.put("message", "NULL"); jsonObject.put("results", new
	 * ArrayList<>()); return new ResponseEntity<>(jsonObject,
	 * HttpStatus.NOT_FOUND); } else { JSONObject jsonObject = new JSONObject();
	 * jsonObject.put("message", "NOT-NULL"); jsonObject.put("results", results);
	 * return new ResponseEntity<>(jsonObject, HttpStatus.OK); } }
	 */

	@RequestMapping(value = "/getResultByStudent/{id}", method = RequestMethod.GET)
	public void getResultByStudent(HttpServletResponse response, @PathVariable("id") int studentId) {

		List<Result> results = resultsService.generateResult(studentId);

		String message = "NOT-NULL";
		if (results == null) {
			message = "NULL";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", message);
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
		jsonObject.put("message", message);
		jsonObject.put("results", results);

		// JSONArray jsonResults = new JSONArray(results);

		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("------------============= RESULTSERVLET JSON ERROR =============---------------");
			e.printStackTrace();
		}
	}
}
