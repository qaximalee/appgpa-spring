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

	/**
	 * This function will return all the Courses.
	 * 
	 * @return List<Course> It will contains all courses data
	 */
	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return this.courseDao.getAllCourses();
	}

	/**
	 * This function will return a Course by course id
	 * 
	 * @param courseId
	 *            Id of a course
	 * @return Course returns the course
	 */
	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDao.getCourseById(id);
	}

	/**
	 * This method will save the course into database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is added successfully or not.
	 */
	@Override
	public boolean save(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDao.save(lookup);
	}

	/**
	 * This method will update the course in the database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is updated successfully or not.
	 */
	@Override
	public boolean update(Course lookup) {
		// TODO Auto-generated method stub
		return this.courseDao.update(lookup);
	}

	/**
	 * This method will delete the course in the database.
	 * 
	 * @param courseId
	 *            id of a course given to this method.
	 * @return boolean if the course is deleted successfully or not.
	 */
	@Override
	public boolean deleteCourseById(int id) {
		// TODO Auto-generated method stub
		return this.courseDao.deleteCourseById(id);
	}

	/**
	 * This method will return the total courses by per semester.
	 * 
	 * @param semesterId
	 *            provide semester id for which the courses are return.
	 * @return List<Course> list of courses against the semester id.
	 */
	@Override
	public List<Course> getCoursesBySemesterId(int semesterId) {
		// TODO Auto-generated method stub
		return this.courseDao.getCoursesBySemesterId(semesterId);
	}
}
