package com.ihsinformatics.spring.appgpa.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;
import com.ihsinformatics.spring.appgpa.model.TopCgpaPOJO;
import com.ihsinformatics.spring.appgpa.service.StudentStatisticsService;

@RestController
@RequestMapping("/student-statistics")
public class StudentStatisticsController {

	@Autowired
	private StudentStatisticsService studentStatisticsService;

	/**
	 * This end point will return a json array in which total no of students will be
	 * return by semester-wise.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getStudentBySemester")
	public String getStudentBySemester() {
		Map<Integer, Integer> studentsBySemester = studentStatisticsService.getTotalStudentsBySemester();

		JSONObject studentJson = new JSONObject(studentsBySemester);
		return studentJson.toString();
	}

	/**
	 * This end point will return all student who have get higher marks by
	 * course-wise.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getCoursesByHigherMarks")
	public String getCoursesByHigherMarks() {
		List<CourseDto> courseDto = studentStatisticsService.getTopCoursesByHigherMarks();

		JSONArray studentJson = new JSONArray(courseDto);
		return studentJson.toString();
	}

	/**
	 * This end point will return total no of courses in all semesters.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getTotalCoursesBySemester")
	public String getTotalCoursesBySemester() {
		List<CoursesBySemesterDto> coursesBySemesterList = studentStatisticsService.getTotalCoursesBySemester();

		JSONArray studentJson = new JSONArray(coursesBySemesterList);
		return studentJson.toString();
	}

	/**
	 * This will return all students with there current semester.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getStudentsCurrentSemester")
	public String getStudentsCurrentSemester() {
		List<StudentSemesterDto> studentCurrentSemester = studentStatisticsService.getStudentsBySemesterCompletion();

		JSONArray studentJson = new JSONArray(studentCurrentSemester);
		return studentJson.toString();
	}

	/**
	 * This end point will return top cgpa holders.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getTopCgpaHolder")
	public String getTopCgpaHolders() {
		List<TopCgpaPOJO> topCgpaHolders = studentStatisticsService.getTopCgpaHolders();

		JSONArray stdJson = new JSONArray();
		for (TopCgpaPOJO topCgpa : topCgpaHolders) {
			JSONObject obj = new JSONObject(topCgpa);
			obj.put("cGpa", topCgpa.getcGpa());
			stdJson.put(obj);
		}
		// JSONArray studentJson = new JSONArray(topCgpaHolders);
		return stdJson.toString();
	}
}
