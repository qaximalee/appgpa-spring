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
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return this.courseDAO.getAll();
	}

	@Override
	public Course getSingle(int id) {
		// TODO Auto-generated method stub
		return this.courseDAO.getSingle(id);
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return this.courseDAO.delete(id);
	}
}
