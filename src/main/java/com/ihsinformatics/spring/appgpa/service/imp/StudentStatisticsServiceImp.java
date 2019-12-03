package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.StudentStatisticsDao;
import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;
import com.ihsinformatics.spring.appgpa.service.StudentStatisticsService;

public class StudentStatisticsServiceImp implements StudentStatisticsService {

	private StudentStatisticsDao studentStatisticsDao;

	@Autowired
	public void setStudentStatisticsDao(StudentStatisticsDao studentStatisticsDao) {
		this.studentStatisticsDao = studentStatisticsDao;
	}

	@Override
	public List<StudentDto> getTopCgpaHolders() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTopCgpaHolders();
	}

	@Override
	public Map<Integer, Integer> getTotalStudentsBySemester() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTotalStudentsBySemester();
	}

	@Override
	public List<CourseDto> getTopCoursesByHigherMarks() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTopCoursesByHigherMarks();
	}

	@Override
	public List<CoursesBySemesterDto> getTotalCoursesBySemester() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTotalCoursesBySemester();
	}

	@Override
	public List<StudentSemesterDto> getStudentsBySemesterCompletion() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getStudentsBySemesterCompletion();
	}
}
