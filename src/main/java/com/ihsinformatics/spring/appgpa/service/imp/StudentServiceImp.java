package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.StudentDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.StudentDAOImp;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

	private StudentDAO studentDAO;

	@Autowired
	public void setStudentDAO(StudentDAOImp studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return this.studentDAO.getAllStudents();
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDAO.getStudentById(id);
	}

	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return this.studentDAO.save(student);
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		return this.studentDAO.update(student);
	}

	@Override
	public boolean deleteStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDAO.deleteStudentById(id);
	}
}
