package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.dao.StudentStatisticsDao;
import com.ihsinformatics.spring.appgpa.dto.CourseDto;
import com.ihsinformatics.spring.appgpa.dto.CoursesBySemesterDto;
import com.ihsinformatics.spring.appgpa.dto.StudentSemesterDto;
import com.ihsinformatics.spring.appgpa.model.TopCgpaPOJO;
import com.ihsinformatics.spring.appgpa.service.StudentStatisticsService;

public class StudentStatisticsServiceImp implements StudentStatisticsService {

	private StudentStatisticsDao studentStatisticsDao;

	@Autowired
	public void setStudentStatisticsDao(StudentStatisticsDao studentStatisticsDao) {
		this.studentStatisticsDao = studentStatisticsDao;
	}

	/**
	 * This method will return the top cgpa holders.
	 * 
	 * @return List<TopCgpaPOJO> top cgpa holders data.
	 */
	@Override
	public List<TopCgpaPOJO> getTopCgpaHolders() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTopCgpaHolders();
	}

	/**
	 * This method will return the total student in each semesters.
	 * 
	 * @return Map<Integer, Integer> first param is the semester no and second is no
	 *         of students.
	 */
	@Override
	public Map<Integer, Integer> getTotalStudentsBySemester() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTotalStudentsBySemester();
	}

	/**
	 * This method will generate the higher marks obtained by students in a course.
	 * 
	 * @return List<CourseDto> it will contains course, marks and student info.
	 */
	@Override
	public List<CourseDto> getTopCoursesByHigherMarks() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTopCoursesByHigherMarks();
	}

	/**
	 * This method will return total no of courses against every semester.
	 * 
	 * @return List<CoursesBySemesterDto> it will contains semester and no of
	 *         courses.
	 */
	@Override
	public List<CoursesBySemesterDto> getTotalCoursesBySemester() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getTotalCoursesBySemester();
	}

	/**
	 * This method will return all students with their completed semester.
	 * 
	 * @return List<StudentSemesterDto> it will contains the semester no and student
	 *         info
	 */
	@Override
	public List<StudentSemesterDto> getStudentsBySemesterCompletion() {
		// TODO Auto-generated method stub
		return this.studentStatisticsDao.getStudentsBySemesterCompletion();
	}
}
