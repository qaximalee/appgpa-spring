package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.SemesterResultsDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.SemesterResultsDAOImp;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

@Service
@Transactional
public class SemesterResultsServiceImp implements SemesterResultsService {

	private SemesterResultsDAO semesterResultsDAO;

	@Autowired
	public void setSemesterResultsDAO(SemesterResultsDAOImp semesterResultsDAO) {
		this.semesterResultsDAO = semesterResultsDAO;
	}

	@Override
	public List<SemesterResults> getAll() {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getAll();
	}

	@Override
	public SemesterResults getSingle(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSingle(id);
	}

	@Override
	public boolean save(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.save(semesterResults);
	}

	@Override
	public boolean update(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.update(semesterResults);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.delete(id);
	}

	@Override
	public List<SemesterResultsPOJO> getAllReadableResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getAllReadableResults();
	}

	@Override
	public List<SemesterResultsPOJO> getStudentSemResults(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getStudentSemResults(studentId);
	}

	@Override
	public boolean getSemesterResultsBy(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemesterResultsBy(studentId, semesterId);
	}

	@Override
	public List<SemesterResults> getSemResEntityByStudent(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemResEntityByStudent(studentId);
	}
}
