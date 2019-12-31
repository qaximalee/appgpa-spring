package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Course;

public interface CourseDao {

	/**
	 * This function will return all the Courses.
	 * 
	 * @return List<Course> It will contains all courses data
	 */
	List<Course> getAllCourses();

	/**
	 * This function will return a Course by course id
	 * 
	 * @param courseId
	 *            Id of a course
	 * @return Course returns the course
	 */
	Course getCourseById(int courseId);

	/**
	 * This method will save the course into database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is added successfully or not.
	 */
	boolean save(Course course);

	/**
	 * This method will update the course in the database.
	 * 
	 * @param course
	 *            course object given to this method.
	 * @return boolean if the course is updated successfully or not.
	 */
	boolean update(Course course);

	/**
	 * This method will delete the course in the database.
	 * 
	 * @param courseId
	 *            id of a course given to this method.
	 * @return boolean if the course is deleted successfully or not.
	 */
	boolean deleteCourseById(int courseId);

	/**
	 * This method will return the total courses by per semester.
	 * 
	 * @param semesterId
	 *            provide semester id for which the courses are return.
	 * @return List<Course> list of courses against the semester id.
	 */
	List<Course> getCoursesBySemesterId(int semesterId);
}
