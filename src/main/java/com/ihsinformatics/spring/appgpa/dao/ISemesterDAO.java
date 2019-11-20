package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Semester;

public interface ISemesterDAO {

	List<Semester> getAll();

	Semester getSingle(int id);

	boolean save(Semester student);

	boolean update(Semester student);

	boolean delete(int id);
}
