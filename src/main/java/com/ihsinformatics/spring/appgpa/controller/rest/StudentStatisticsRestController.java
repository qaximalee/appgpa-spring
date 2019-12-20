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

	/**
	 * This end point will return a json array in which total no of students will be
	 * return by semester-wise.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getStudentBySemester")
	public Map<Integer, Integer> getStudentBySemester() {
		return studentStatisticsService.getTotalStudentsBySemester();
	}

	/**
	 * This end point will return all student who have get higher marks by
	 * course-wise.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getCoursesByHigherMarks")
	public List<CourseDto> getCoursesByHigherMarks() {
		return studentStatisticsService.getTopCoursesByHigherMarks();
	}

	/**
	 * This end point will return total no of courses in all semesters.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getTotalCoursesBySemester")
	public List<CoursesBySemesterDto> getTotalCoursesBySemester() {
		return studentStatisticsService.getTotalCoursesBySemester();
	}

	/**
	 * This will return all students with there current semester.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getStudentsCurrentSemester")
	public List<StudentSemesterDto> getStudentsCurrentSemester() {
		return studentStatisticsService.getStudentsBySemesterCompletion();
	}

	/**
	 * This end point will return top cgpa holders.
	 * 
	 * @return String JSONArray
	 */
	@GetMapping("/getTopCgpaHolder")
	public List<TopCgpaPOJO> getTopCgpaHolders() {
		return studentStatisticsService.getTopCgpaHolders();
	}
}
