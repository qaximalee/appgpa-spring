package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Student;

public interface StudentDAO {

	List<Student> getAll();

	Student getSingle(int id);

	boolean save(Student student);

	boolean update(Student student);

	boolean delete(int id);
}
