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

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@Controller
@RequestMapping("/result")
public class ResultController {

	private int CREDIT_HOUR = 3;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private CourseResultsService courseResultsService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewResultsForm() {
		return new ModelAndView("/overall_results/generate_result").addObject("studentList",
				studentService.getAllStudents());
	}

	@RequestMapping(value = "/getResultByStudent", method = RequestMethod.GET)
	public void getResultByStudent(HttpServletResponse response, @RequestParam("id") int studentId) {
		List<SemesterResults> list = semesterResultsService.getSemResEntityByStudent(studentId);
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
					.getAllCourseResultsBySemester(list.get(counter).getSemester().getSemesterId(), studentId);
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
