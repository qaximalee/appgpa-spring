package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Student;

public interface StudentService {

	public List<Student> getAllStudents();

	public Student getStudentById(int id);

	public boolean save(Student student);

	public boolean update(Student student);

	public boolean deleteStudentById(int id);
}
