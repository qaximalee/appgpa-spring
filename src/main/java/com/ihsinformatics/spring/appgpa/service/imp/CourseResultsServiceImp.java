package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.CourseResultsDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.CourseResultsDAOImp;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;

@Service
@Transactional
public class CourseResultsServiceImp implements CourseResultsService {

	private CourseResultsDAO courseResultsDAO;

	@Autowired
	public void setCourseResultsDAO(CourseResultsDAOImp courseResultsDAO) {
		this.courseResultsDAO = courseResultsDAO;
	}

	@Override
	public List<CourseResults> getAll() {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getAll();
	}

	@Override
	public CourseResults getSingle(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getSingle(id);
	}

	@Override
	public boolean save(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.save(courseResults);
	}

	@Override
	public boolean update(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.update(courseResults);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.delete(id);
	}

	@Override
	public List<CourseResults> getAllCourseResultsBySemester(int semesterId, int studentId) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getAllCourseResultsBySemester(semesterId, studentId);
	}

	@Override
	public List<CourseResultsPOJO> getAllReadableResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getAllReadableResults();
	}

}
