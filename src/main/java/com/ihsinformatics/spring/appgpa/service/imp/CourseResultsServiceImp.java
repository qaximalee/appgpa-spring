package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.CourseResultsDao;
import com.ihsinformatics.spring.appgpa.dao.imp.CourseResultsDaoImp;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;

@Service
@Transactional
public class CourseResultsServiceImp implements CourseResultsService {

	private CourseResultsDao courseResultsDao;

	@Autowired
	public void setCourseResultsDao(CourseResultsDaoImp courseResultsDao) {
		this.courseResultsDao = courseResultsDao;
	}

	@Override
	public List<CourseResults> getAllCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getAllCourseResults();
	}

	@Override
	public CourseResults getCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getCourseResultsById(id);
	}

	@Override
	public boolean save(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.save(courseResults);
	}

	@Override
	public boolean update(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.update(courseResults);
	}

	@Override
	public boolean deleteCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.deleteCourseResultsById(id);
	}

	@Override
	public List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getCourseResultsByStudentAndSemesterId(semesterId, studentId);
	}

	@Override
	public List<CourseResultsPOJO> getAllReadableCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getAllReadableCourseResults();
	}

	@Override
	public CourseResults calculateCourseResult(int studentId, int semesterId, int courseId, double percentage) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.calculateCourseResult(studentId, semesterId, courseId, percentage);
	}
}
