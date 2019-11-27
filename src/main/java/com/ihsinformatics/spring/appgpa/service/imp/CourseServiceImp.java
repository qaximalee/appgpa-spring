package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.CourseDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.CourseDAOImp;
import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.service.CourseService;

@Service
@Transactional
public class CourseServiceImp implements CourseService {

	private CourseDAO courseDAO;

	@Autowired
	public void setCourseDAO(CourseDAOImp courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return this.courseDAO.getAllCourses();
	}

	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDAO.getCourseById(id);
	}

	@Override
	public boolean save(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDAO.save(lookup);
	}

	@Override
	public boolean update(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDAO.update(lookup);
	}

	@Override
	public boolean deleteCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDAO.deleteCourseById(id);
	}

	@Override
	public List<Course> getCoursesBySemesterId(int semesterId) {
		// TODO Auto-generated method stub
		return this.courseDAO.getCoursesBySemesterId(semesterId);
	}
}
