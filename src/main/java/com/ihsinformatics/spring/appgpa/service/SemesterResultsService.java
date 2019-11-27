package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

public interface SemesterResultsService {

	List<SemesterResults> getAllSemesterResults();

	SemesterResults getSemesterResultsById(int id);

	boolean save(SemesterResults semesterResults);

	boolean update(SemesterResults semesterResults);

	boolean deleteSemesterResultsById(int id);

	List<SemesterResultsPOJO> getAllReadableSemesterResults();

	List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId);

	boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId);

	List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId);
}
