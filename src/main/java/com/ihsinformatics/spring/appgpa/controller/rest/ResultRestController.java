package com.ihsinformatics.spring.appgpa.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@RestController
@RequestMapping("/rest-result")
public class ResultRestController {

	private int CREDIT_HOUR = 3;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private CourseResultsService courseResultsService;

	@PostMapping("/getResultByStudent/{id}")
	public void getResultByStudent(HttpServletResponse response, @PathVariable("id") int studentId) {
		List<SemesterResults> list = semesterResultsService.getSemesterResultsEntityByStudentId(studentId);
		List<Result> results = new ArrayList<>();
		String message = "NOT-NULL";
		if (list.size() <= 0) {
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

		int counter = 0;
		while (counter < list.size()) {
			List<CourseResults> courseResults = courseResultsService
					.getCourseResultsByStudentAndSemesterId(list.get(counter).getSemester().getSemesterId(), studentId);
			for (CourseResults courseResult : courseResults) {
				Result result = new Result(list.get(counter).getSemesterResultId(),
						courseResult.getCourse().getCourseCode(), courseResult.getCourse().getName(),
						list.get(counter).getSemester().getSemesterNo(), courseResult.getPercentage(), CREDIT_HOUR,
						courseResult.getGpa(), courseResult.getGrade(), courseResult.getTotalPoints(),
						list.get(counter).getSemesterGPA(), list.get(counter).getcGPA());
				results.add(result);
			}
			counter++;
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
