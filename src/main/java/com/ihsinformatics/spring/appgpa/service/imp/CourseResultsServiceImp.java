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

	/**
	 * This method will get all course results in the database.
	 * 
	 * @return List<CourseResults>
	 */
	@Override
	public List<CourseResults> getAllCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getAllCourseResults();
	}

	/**
	 * This method will get Course Results by course results id.
	 * 
	 * @param int
	 *            id
	 * @return SemesterResults
	 */
	@Override
	public CourseResults getCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getCourseResultsById(id);
	}

	/**
	 * This method will save entity of CourseResults and return whether the entity
	 * is saved or not.
	 * 
	 * @param courseResults
	 *            this entity will be saved in database.
	 * @return boolean if the entity is saved or not.
	 */
	@Override
	public boolean save(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.save(courseResults);
	}

	/**
	 * This method will update an existing entity by providing the updated entity.
	 * 
	 * @param courseResults
	 *            updated course result
	 * @return boolean whether if the entity updated or not.
	 */
	@Override
	public boolean update(CourseResults courseResults) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.update(courseResults);
	}

	/**
	 * This method will delete an entity from database by providing the id of course
	 * results.
	 * 
	 * @param id
	 *            course results id.
	 * @return boolean whether deleted or not.
	 */
	@Override
	public boolean deleteCourseResultsById(int id) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.deleteCourseResultsById(id);
	}

	/**
	 * This method will give the Course Results of a student by it's student id and
	 * semester id.
	 * 
	 * @param semesterId
	 * @param studentId
	 * 
	 * @return List<CourseResults> list of course results against above parameters
	 */
	@Override
	public List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getCourseResultsByStudentAndSemesterId(semesterId, studentId);
	}

	/**
	 * This will return the all Course results in a readable entity for showing on
	 * web.
	 * 
	 * @return List<CourseResultsPOJO>
	 */
	@Override
	public List<CourseResultsPOJO> getAllReadableCourseResults() {
		// TODO Auto-generated method stub
		return this.courseResultsDao.getAllReadableCourseResults();
	}

	/**
	 * This method will calculate the CourseResult for saving the course results in
	 * database.
	 * 
	 * @param studentId
	 * @param semesterId
	 * @param courseId
	 * @param percentage
	 *            It is the percentage obtain in the course
	 * 
	 * @return CourseResults
	 */
	@Override
	public CourseResults calculateCourseResult(int studentId, int semesterId, int courseId, double percentage) {
		// TODO Auto-generated method stub
		return this.courseResultsDao.calculateCourseResult(studentId, semesterId, courseId, percentage);
	}
}
