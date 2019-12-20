package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

public interface SemesterResultsDao {

	/**
	 * This method will get all semester results in the database.
	 * 
	 * @return List<SemesterResults>
	 */
	List<SemesterResults> getAllSemesterResults();

	/**
	 * This method will get Semester Results by semester results id.
	 * 
	 * @param int
	 *            id
	 * @return SemesterResults
	 */
	SemesterResults getSemesterResultsById(int id);

	/**
	 * This method will save the Semester Results.
	 * 
	 * @param SemesterResults
	 *            data
	 * @return boolean
	 */
	boolean save(SemesterResults semesterResults);

	/**
	 * This method will update the Semester Results
	 * 
	 * @param SemesterResults
	 *            semesterResults
	 * @return boolean
	 */
	boolean update(SemesterResults semesterResults);

	/**
	 * This method will delete Semester Results.
	 * 
	 * @param int
	 *            id
	 * @return boolean
	 */
	boolean deleteSemesterResultsById(int id);

	/**
	 * This method gets all data of semester results in a readable form which is
	 * showed in the view all semester results
	 * 
	 * @return List<SemesterResultsPOJO>
	 */
	List<SemesterResultsPOJO> getAllReadableSemesterResults();

	/**
	 * This method will be called when a new semester result is added and the
	 * student has any previous semester results.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResultsPOJO>
	 */
	List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId);

	/**
	 * This method will be checking the semester result added by the student is the
	 * same as previous semester's result.
	 * 
	 * @param int
	 *            studentId, int semesterId
	 * @return boolean
	 */
	boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId);

	/**
	 * This method will get all semester results by student id and return in non
	 * readable form means it will return in SemesterResults Entity.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResults>
	 */
	List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId);

	/**
	 * It will calculate the results for a student and return it to the calling
	 * controller(s).
	 * 
	 * @param semesterId
	 * @param studentId
	 * @return'
	 */
	public SemesterResults calculateSemesterResults(int studentId, int semesterId);
}
