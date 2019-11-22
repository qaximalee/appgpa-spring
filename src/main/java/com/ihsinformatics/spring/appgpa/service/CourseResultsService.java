package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public interface CourseResultsService {

	public List<CourseResults> getAll();

	public CourseResults getSingle(int id);

	public boolean save(CourseResults courseResults);

	public boolean update(CourseResults courseResults);

	public boolean delete(int id);

	List<CourseResults> getAllCourseResultsBySemester(int semesterId, int studentId);

	List<CourseResultsPOJO> getAllReadableResults();
}
