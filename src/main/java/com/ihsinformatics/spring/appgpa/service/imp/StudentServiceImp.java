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

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return this.studentDao.getAllStudents();
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDao.getStudentById(id);
	}

	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return this.studentDao.save(student);
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		return this.studentDao.update(student);
	}

	@Override
	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDao.deleteStudentById(id);
	}
}
