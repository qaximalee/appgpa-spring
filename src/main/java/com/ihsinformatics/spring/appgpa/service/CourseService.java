package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Course;

public interface CourseService {

	List<Course> getAllCourses();

	Course getCourseById(int id);

	boolean save(Course course);

	boolean update(Course course);

	boolean deleteCourseById(int id);

	List<Course> getCoursesBySemesterId(int semesterId);
}
