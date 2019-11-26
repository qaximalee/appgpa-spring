package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Semester;

public interface SemesterService {

	public List<Semester> getAllSemester();

	public Semester getSemesterById(int id);

	public boolean save(Semester semester);

	public boolean update(Semester semester);

	public boolean deleteSemesterById(int id);
}
