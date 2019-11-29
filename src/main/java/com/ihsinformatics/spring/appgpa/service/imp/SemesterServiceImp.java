package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.SemesterDao;
import com.ihsinformatics.spring.appgpa.dao.imp.SemesterDaoImp;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;

@Service
@Transactional
public class SemesterServiceImp implements SemesterService {

	private SemesterDao semesterDao;

	@Autowired
	public void setSemesterDao(SemesterDaoImp semesterDao) {
		this.semesterDao = semesterDao;
	}

	@Override
	public List<Semester> getAllSemester() {
		// TODO Auto-generated method stub
		return this.semesterDao.getAllSemesters();
	}

	@Override
	public Semester getSemesterById(int id) {
		// TODO Auto-generated method stub
		return this.semesterDao.getSemesterById(id);
	}

	@Override
	public boolean save(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDao.save(semester);
	}

	@Override
	public boolean update(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDao.update(semester);
	}

	@Override
	public boolean deleteSemesterById(int id) {
		// TODO Auto-generated method stub
		return this.semesterDao.deleteSemesterById(id);
	}
}
