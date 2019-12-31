package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Student;

public interface StudentDao {

	/**
	 * This will return all Students from the database.
	 * 
	 * @return List<Student>
	 */
	List<Student> getAllStudents();

	/**
	 * This will give single student info from the database.
	 * 
	 * @param id
	 * @return Student
	 */
	Student getStudentById(int id);

	/**
	 * This method will save an student.
	 * 
	 * @param student
	 *            updated student entity
	 * @return boolean whether the student saved or not.
	 */
	boolean save(Student student);

	/**
	 * This method will update a student on the data.
	 * 
	 * @param student
	 *            updated entity
	 * @return boolean
	 */
	boolean update(Student student);

	/**
	 * This will return true if the student successfully deleted or not.
	 * 
	 * @param student
	 *            id provided.
	 * @return boolean
	 */
	boolean deleteStudentById(int id);
}
