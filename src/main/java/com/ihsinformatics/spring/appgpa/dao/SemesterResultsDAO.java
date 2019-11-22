package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

public interface SemesterResultsDAO {

	List<SemesterResults> getAll();

	SemesterResults getSingle(int id);

	boolean save(SemesterResults semesterResults);

	boolean update(SemesterResults semesterResults);

	boolean delete(int id);

	List<SemesterResultsPOJO> getAllReadableResults();

	List<SemesterResultsPOJO> getStudentSemResults(int studentId);

	boolean getSemesterResultsBy(int studentId, int semesterId);

	List<SemesterResults> getSemResEntityByStudent(int studentId);
}
