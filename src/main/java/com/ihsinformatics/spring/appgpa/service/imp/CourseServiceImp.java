package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.CourseDao;
import com.ihsinformatics.spring.appgpa.dao.imp.CourseDaoImp;
import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.service.CourseService;

@Service
@Transactional
public class CourseServiceImp implements CourseService {

	private CourseDao courseDao;

	@Autowired
	public void setCourseDao(CourseDaoImp courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return this.courseDao.getAllCourses();
	}

	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDao.getCourseById(id);
	}

	@Override
	public boolean save(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDao.save(lookup);
	}

	@Override
	public boolean update(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDao.update(lookup);
	}

	@Override
	public boolean deleteCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDao.deleteCourseById(id);
	}

	@Override
	public List<Course> getCoursesBySemesterId(int semesterId) {
		// TODO Auto-generated method stub
		return this.courseDao.getCoursesBySemesterId(semesterId);
	}
}
