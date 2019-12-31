package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

public interface CourseResultsService {

	/**
	 * This method will get all course results in the database.
	 * 
	 * @return List<CourseResults>
	 */
	List<CourseResults> getAllCourseResults();

	/**
	 * This method will get Course Results by course results id.
	 * 
	 * @param int
	 *            id
	 * @return SemesterResults
	 */
	CourseResults getCourseResultsById(int id);

	/**
	 * This method will save entity of CourseResults and return whether the entity
	 * is saved or not.
	 * 
	 * @param courseResults
	 *            this entity will be saved in database.
	 * @return boolean if the entity is saved or not.
	 */
	boolean save(CourseResults courseResults);

	/**
	 * This method will update an existing entity by providing the updated entity.
	 * 
	 * @param courseResults
	 *            updated course result
	 * @return boolean whether if the entity updated or not.
	 */
	boolean update(CourseResults courseResults);

	/**
	 * This method will delete an entity from database by providing the id of course
	 * results.
	 * 
	 * @param id
	 *            course results id.
	 * @return boolean whether deleted or not.
	 */
	boolean deleteCourseResultsById(int id);

	/**
	 * This method will give the Course Results of a student by it's student id and
	 * semester id.
	 * 
	 * @param semesterId
	 * @param studentId
	 * 
	 * @return List<CourseResults> list of course results against above parameters
	 */
	List<CourseResults> getCourseResultsByStudentAndSemesterId(int semesterId, int studentId);

	/**
	 * This will return the all Course results in a readable entity for showing on
	 * web.
	 * 
	 * @return List<CourseResultsPOJO>
	 */
	List<CourseResultsPOJO> getAllReadableCourseResults();

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
	CourseResults calculateCourseResult(int studentId, int semesterId, int courseId, double percentage);
}
