package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.SemesterDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.SemesterDAOImp;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;

@Service
@Transactional
public class SemesterServiceImp implements SemesterService {

	private SemesterDAO semesterDAO;

	@Autowired
	public void setSemesterDAO(SemesterDAOImp semesterDAO) {
		this.semesterDAO = semesterDAO;
	}

	@Override
	public List<Semester> getAll() {
		// TODO Auto-generated method stub
		return this.semesterDAO.getAll();
	}

	@Override
	public Semester getSingle(int id) {
		// TODO Auto-generated method stub
		return this.semesterDAO.getSingle(id);
	}

	@Override
	public boolean save(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDAO.save(semester);
	}

	@Override
	public boolean update(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDAO.update(semester);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return this.semesterDAO.delete(id);
	}
}
