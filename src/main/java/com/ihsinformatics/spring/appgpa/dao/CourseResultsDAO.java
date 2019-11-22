package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public interface CourseResultsDAO {

	List<CourseResults> getAll();

	CourseResults getSingle(int id);

	boolean save(CourseResults courseResults);

	boolean update(CourseResults courseResults);

	boolean delete(int id);

	List<CourseResults> getAllCourseResultsBySemester(int semesterId, int studentId);

	List<CourseResultsPOJO> getAllReadableResults();
}
