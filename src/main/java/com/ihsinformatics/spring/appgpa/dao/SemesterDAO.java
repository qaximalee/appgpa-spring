package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Semester;

public interface SemesterDAO {

	List<Semester> getAllSemesters();

	Semester getSemesterById(int id);

	boolean save(Semester semester);

	boolean update(Semester semester);

	boolean deleteSemesterById(int id);
}
