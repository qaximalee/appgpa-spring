package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Student;

public interface SStudentService {

	public List<Student> getAll();

	public Student getSingle(int id);

	public boolean save(Student student);

	public boolean update(Student student);

	public boolean delete(int id);
}
