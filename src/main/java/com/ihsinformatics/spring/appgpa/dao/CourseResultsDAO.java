package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public interface CourseResultsDAO {

	List<CourseResults> getAllCourseResults();

	CourseResults getCourseResultsById(int id);

	boolean save(CourseResults courseResults);

	boolean update(CourseResults courseResults);

	boolean deleteCourseResultsById(int id);

	List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId);

	List<CourseResultsPOJO> getAllReadableCourseResults();
}
