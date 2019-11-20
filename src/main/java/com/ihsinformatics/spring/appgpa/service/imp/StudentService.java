package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.SStudentService;

@Service
public class StudentService implements SStudentService {

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
