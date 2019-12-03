package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;
import java.util.Map;

import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;

public interface StudentStatisticsDao {

	List<StudentDto> getTopCgpaHolders();

	Map<Integer, Integer> getTotalStudentsBySemester();

	List<CourseDto> getTopCoursesByHigherMarks();

	List<CoursesBySemesterDto> getTotalCoursesBySemester();

	List<StudentSemesterDto> getStudentsBySemesterCompletion();
}
