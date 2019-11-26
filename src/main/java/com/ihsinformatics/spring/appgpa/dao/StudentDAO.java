package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Student;

public interface StudentDAO {

	List<Student> getAllStudents();

	Student getStudentById(int id);

	boolean save(Student student);

	boolean update(Student student);

	boolean deleteStudentById(int id);
}
