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
	public List<SemesterResults> getAllSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getAllSemesterResults();
	}

	@Override
	public SemesterResults getSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemesterResultsById(id);
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
	public boolean deleteSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.deleteSemesterResultsById(id);
	}

	@Override
	public List<SemesterResultsPOJO> getAllReadableSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getAllReadableSemesterResults();
	}

	@Override
	public List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemesterResultsByStudentId(studentId);
	}

	@Override
	public boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemesterResultsByStudentAndSemesterId(studentId, semesterId);
	}

	@Override
	public List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDAO.getSemesterResultsEntityByStudentId(studentId);
	}
}
