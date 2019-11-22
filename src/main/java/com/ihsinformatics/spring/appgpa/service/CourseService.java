package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Course;

public interface CourseService {

	public List<Course> getAll();

	public Course getSingle(int id);

	public boolean save(Course course);

	public boolean update(Course course);

	public boolean delete(int id);
}
