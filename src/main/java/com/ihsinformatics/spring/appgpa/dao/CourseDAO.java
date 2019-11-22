package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Course;

public interface CourseDAO {

	List<Course> getAll();

	Course getSingle(int id);

	boolean save(Course course);

	boolean update(Course course);

	boolean delete(int id);

	List<Course> getCoursesBySemester(int semesterId);
}
