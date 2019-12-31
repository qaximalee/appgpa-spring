package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.StudentDao;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

	private StudentDao studentDao;

	@Autowired
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	/**
	 * This will return all Students from the database.
	 * 
	 * @return List<Student>
	 */
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return this.studentDao.getAllStudents();
	}

	/**
	 * This will give single student info from the database.
	 * 
	 * @param id
	 * @return Student
	 */
	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDao.getStudentById(id);
	}

	/**
	 * This method will save an student.
	 * 
	 * @param student
	 *            updated student entity
	 * @return boolean whether the student saved or not.
	 */
	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return this.studentDao.save(student);
	}

	/**
	 * This method will update a student on the data.
	 * 
	 * @param student
	 *            updated entity
	 * @return boolean
	 */
	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		return this.studentDao.update(student);
	}

	/**
	 * This will return true if the student successfully deleted or not.
	 * 
	 * @param student
	 *            id provided.
	 * @return boolean
	 */
	@Override
	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDao.deleteStudentById(id);
	}
}
