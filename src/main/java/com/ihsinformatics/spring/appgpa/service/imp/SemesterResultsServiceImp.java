package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.SemesterResultsDao;
import com.ihsinformatics.spring.appgpa.dao.imp.SemesterResultsDaoImp;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

@Service
@Transactional
public class SemesterResultsServiceImp implements SemesterResultsService {

	private SemesterResultsDao semesterResultsDao;

	@Autowired
	public void setSemesterResultsDao(SemesterResultsDaoImp semesterResultsDao) {
		this.semesterResultsDao = semesterResultsDao;
	}

	/**
	 * This method will get all semester results in the database.
	 * 
	 * @return List<SemesterResults>
	 */
	@Override
	public List<SemesterResults> getAllSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getAllSemesterResults();
	}

	/**
	 * This method will get Semester Results by semester results id.
	 * 
	 * @param semesterId
	 * @return SemesterResults
	 */
	@Override
	public SemesterResults getSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsById(id);
	}

	/**
	 * This method will save the Semester Results.
	 * 
	 * @param semesterResults
	 * 
	 * @return boolean
	 */
	@Override
	public boolean save(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.save(semesterResults);
	}

	/**
	 * This method will update the Semester Results
	 * 
	 * @param semesterResults
	 * @return boolean
	 */
	@Override
	public boolean update(SemesterResults semesterResults) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.update(semesterResults);
	}

	/**
	 * This method will delete Semester Results.
	 * 
	 * @param semesterResultsId
	 * @return boolean
	 */
	@Override
	public boolean deleteSemesterResultsById(int id) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.deleteSemesterResultsById(id);
	}

	/**
	 * This method gets all data of semester results in a readable form which is
	 * showed in the view all semester results
	 * 
	 * @return List<SemesterResultsPOJO> It will contains all readable values of
	 *         semester results.
	 */
	@Override
	public List<SemesterResultsPOJO> getAllReadableSemesterResults() {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getAllReadableSemesterResults();
	}

	/**
	 * This method will be called when a new semester result is added and the
	 * student has any previous semester results.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResultsPOJO>
	 */
	@Override
	public List<SemesterResultsPOJO> getSemesterResultsByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsByStudentId(studentId);
	}

	/**
	 * This method will be checking the semester result added by the student is the
	 * same as previous semester's result.
	 * 
	 * @param int
	 *            studentId, int semesterId
	 * @return boolean
	 */
	@Override
	public boolean getSemesterResultsByStudentAndSemesterId(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsByStudentAndSemesterId(studentId, semesterId);
	}

	/**
	 * This method will get all semester results by student id and return in non
	 * readable form means it will return in SemesterResults Entity.
	 * 
	 * @param int
	 *            studentId
	 * @return List<SemesterResults>
	 */
	@Override
	public List<SemesterResults> getSemesterResultsEntityByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.getSemesterResultsEntityByStudentId(studentId);
	}

	/**
	 * It will calculate the results for a student and return it to the calling
	 * controller(s).
	 * 
	 * @param semesterId
	 * @param studentId
	 * @return'
	 */
	@Override
	public SemesterResults calculateSemesterResults(int studentId, int semesterId) {
		// TODO Auto-generated method stub
		return this.semesterResultsDao.calculateSemesterResults(studentId, semesterId);
	}
}
