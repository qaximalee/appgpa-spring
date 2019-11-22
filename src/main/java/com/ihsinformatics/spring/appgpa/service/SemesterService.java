package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Semester;

public interface SemesterService {

	public List<Semester> getAll();

	public Semester getSingle(int id);

	public boolean save(Semester semester);

	public boolean update(Semester semester);

	public boolean delete(int id);
}
