package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Semester;

public interface SemesterService {

	/**
	 * This will return all Semester from the database.
	 * 
	 * @return List<Semester>
	 */
	List<Semester> getAllSemesters();

	/**
	 * This will give single Semester info from the database.
	 * 
	 * @param id
	 *            semester id
	 * @return Semester entity
	 */
	Semester getSemesterById(int id);

	/**
	 * This method will save an Semester.
	 * 
	 * @param semester
	 *            updated semester entity
	 * @return boolean whether the Semester saved or not.
	 */
	boolean save(Semester semester);

	/**
	 * This method will update a semester on the data.
	 * 
	 * @param semester
	 *            entity
	 * @return boolean up
	 */
	boolean update(Semester semester);

	/**
	 * This will return true if the student successfully saved or not.
	 * 
	 * @param id
	 *            semester id provided.
	 * @return boolean
	 */
	boolean deleteSemesterById(int id);
}
