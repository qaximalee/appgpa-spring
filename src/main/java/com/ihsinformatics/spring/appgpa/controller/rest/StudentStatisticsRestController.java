package com.ihsinformatics.spring.appgpa.controller.rest;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/rest-student-statistics")
public class StudentStatisticsRestController {

	@Autowired
	private StudentStatisticsService studentStatisticsService;

	public void setStudentStatisticsService(StudentStatisticsService studentStatisticsService) {
		this.studentStatisticsService = studentStatisticsService;
	}

	@GetMapping("/getStudentBySemester")
	public Map<Integer, Integer> getStudentBySemester() {
		return studentStatisticsService.getTotalStudentsBySemester();
	}

	@GetMapping("/getCoursesByHigherMarks")
	public List<CourseDto> getCoursesByHigherMarks() {
		return studentStatisticsService.getTopCoursesByHigherMarks();
	}

	@GetMapping("/getTotalCoursesBySemester")
	public List<CoursesBySemesterDto> getTotalCoursesBySemester() {
		return studentStatisticsService.getTotalCoursesBySemester();
	}

	@GetMapping("/getStudentsCurrentSemester")
	public List<StudentSemesterDto> getStudentsCurrentSemester() {
		return studentStatisticsService.getStudentsBySemesterCompletion();
	}

	@GetMapping("/getTopCgpaHolder")
	public List<TopCgpaPOJO> getTopCgpaHolders() {
		return studentStatisticsService.getTopCgpaHolders();// stdJson.toString();
	}
}
