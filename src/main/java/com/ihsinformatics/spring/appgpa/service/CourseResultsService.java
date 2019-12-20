package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public interface CourseResultsService {

	public List<CourseResults> getAllCourseResults();

	public CourseResults getCourseResultsById(int id);

	public boolean save(CourseResults courseResults);

	public boolean update(CourseResults courseResults);

	public boolean deleteCourseResultsById(int id);

	List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId);

	List<CourseResultsPOJO> getAllReadableCourseResults();

	CourseResults calculateCourseResult(int studentId, int semesterId, int courseId, double percentage);
}
