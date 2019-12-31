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

	/**
	 * This will return all Semester from the database.
	 * 
	 * @return List<Semester>
	 */
	@Override
	public List<Semester> getAllSemesters() {
		// TODO Auto-generated method stub
		return this.semesterDao.getAllSemesters();
	}

	/**
	 * This will give single Semester info from the database.
	 * 
	 * @param id
	 *            semester id
	 * @return Semester entity
	 */
	@Override
	public Semester getSemesterById(int id) {
		// TODO Auto-generated method stub
		return this.semesterDao.getSemesterById(id);
	}

	/**
	 * This method will save an Semester.
	 * 
	 * @param semester
	 *            updated semester entity
	 * @return boolean whether the Semester saved or not.
	 */
	@Override
	public boolean save(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDao.save(semester);
	}

	/**
	 * This method will update a semester on the data.
	 * 
	 * @param semester
	 *            entity
	 * @return boolean up
	 */
	@Override
	public boolean update(Semester semester) {
		// TODO Auto-generated method stub
		return this.semesterDao.update(semester);
	}

	/**
	 * This will return true if the student successfully saved or not.
	 * 
	 * @param id
	 *            semester id provided.
	 * @return boolean
	 */
	@Override
	public boolean deleteSemesterById(int id) {
		// TODO Auto-generated method stub
		return this.semesterDao.deleteSemesterById(id);
	}
}
