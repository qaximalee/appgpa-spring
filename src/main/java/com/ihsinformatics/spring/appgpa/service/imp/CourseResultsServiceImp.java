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
	public List<CourseResults> getAllCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getAllCourseResults();
	}

	@Override
	public CourseResults getCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getCourseResultsById(id);
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
	public boolean deleteCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.deleteCourseResultsById(id);
	}

	@Override
	public List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId) {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getCourseResultsByStudentAndSemesterId(semesterId, studentId);
	}

	@Override
	public List<CourseResultsPOJO> getAllReadableCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDAO.getAllReadableCourseResults();
	}

}
