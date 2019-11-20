package com.ihsinformatics.spring.appgpa.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ihsinformatics.spring.appgpa.dao.IStudentDAO;
import com.ihsinformatics.spring.appgpa.model.Student;

@Repository
public class StudentDAO implements IStudentDAO {

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getSingle(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
