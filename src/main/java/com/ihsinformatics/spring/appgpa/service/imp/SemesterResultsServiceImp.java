package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.SemesterResultsDao;
import com.ihsinformatics.spring.appgpa.dao.imp.SemesterResultsDaoImp;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

@Service
@Transactional
public class SemesterResultsServiceImp implements SemesterResultsService {

	private SemesterResultsDao semesterResultsDao;

	@Autowired
	public void setSemesterResultsDao(SemesterResultsDaoImp semesterResultsDao) {
		this.semesterResultsDao = semesterResultsDao;
	}

	@Override
	public List<SemesterResults> getAllSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getAllSemesterResults();
	}

	@Override
	public SemesterResults getSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsById(id);
	}

	@Override
	public boolean save(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.save(semesterResults);
	}

	@Override
	public boolean update(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.update(semesterResults);
	}

	@Override
	public boolean deleteSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.deleteSemesterResultsById(id);
	}

	@Override
	public List<SemesterResultsPOJO> getAllReadableSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getAllReadableSemesterResults();
	}

	@Override
	public List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsByStudentId(studentId);
	}

	@Override
	public boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsByStudentAndSemesterId(studentId, semesterId);
	}

	@Override
	public List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsEntityByStudentId(studentId);
	}

	@Override
	public SemesterResults calculateSemesterResults(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.calculateSemesterResults(studentId, semesterId);
	}
}
