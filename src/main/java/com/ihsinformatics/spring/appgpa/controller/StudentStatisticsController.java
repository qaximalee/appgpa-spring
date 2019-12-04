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
import com.ihsinformatics.spring.appgpa.service.StudentStatisticsService;

@RestController
@RequestMapping("/student-statistics")
public class StudentStatisticsController {

	@Autowired
	private StudentStatisticsService studentStatisticsService;

	public void setStudentStatisticsService(StudentStatisticsService studentStatisticsService) {
		this.studentStatisticsService = studentStatisticsService;
	}

	@GetMapping("/getStudentBySemester")
	public String getStudentBySemester() {
		Map<Integer, Integer> studentsBySemester = studentStatisticsService.getTotalStudentsBySemester();
		// System.out.println(studentsBySemester.toString());

		JSONObject studentJson = new JSONObject(studentsBySemester);
		return studentJson.toString();
	}

	@GetMapping("/getCoursesByHigherMarks")
	public String getCoursesByHigherMarks() {
		List<CourseDto> courseDto = studentStatisticsService.getTopCoursesByHigherMarks();

		/*
		 * for (CourseDto cDto : courseDto) System.out.println("Course Name: " +
		 * cDto.getCourse().getName() + "Top Percentage: " + cDto.getMarks() +
		 * "Student Info: " + cDto.getStudents().getFirstName());
		 */
		JSONArray studentJson = new JSONArray(courseDto);
		return studentJson.toString();
	}

	@GetMapping("/getTotalCoursesBySemester")
	public String getTotalCoursesBySemester() {
		List<CoursesBySemesterDto> coursesBySemesterList = studentStatisticsService.getTotalCoursesBySemester();

		JSONArray studentJson = new JSONArray(coursesBySemesterList);
		return studentJson.toString();
	}

	@GetMapping("/getStudentsCurrentSemester")
	public String getStudentsCurrentSemester() {
		List<StudentSemesterDto> studentCurrentSemester = studentStatisticsService.getStudentsBySemesterCompletion();

		JSONArray studentJson = new JSONArray(studentCurrentSemester);
		return studentJson.toString();
	}
}
