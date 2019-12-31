package com.ihsinformatics.spring.appgpa.service;

import java.util.List;
import java.util.Map;

import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;
import com.ihsinformatics.spring.appgpa.model.TopCgpaPOJO;

public interface StudentStatisticsService {

	/**
	 * This method will return the top cgpa holders.
	 * 
	 * @return List<TopCgpaPOJO> top cgpa holders data.
	 */
	List<TopCgpaPOJO> getTopCgpaHolders();

	/**
	 * This method will return the total student in each semesters.
	 * 
	 * @return Map<Integer, Integer> first param is the semester no and second is no
	 *         of students.
	 */
	Map<Integer, Integer> getTotalStudentsBySemester();

	/**
	 * This method will generate the higher marks obtained by students in a course.
	 * 
	 * @return List<CourseDto> it will contains course, marks and student info.
	 */
	List<CourseDto> getTopCoursesByHigherMarks();

	/**
	 * This method will return total no of courses against every semester.
	 * 
	 * @return List<CoursesBySemesterDto> it will contains semester and no of
	 *         courses.
	 */
	List<CoursesBySemesterDto> getTotalCoursesBySemester();

	/**
	 * This method will return all students with their completed semester.
	 * 
	 * @return List<StudentSemesterDto> it will contains the semester no and student
	 *         info
	 */
	List<StudentSemesterDto> getStudentsBySemesterCompletion();
}
