package com.ihsinformatics.spring.appgpa.dto;

import com.ihsinformatics.spring.appgpa.model.Semester;

public class CoursesBySemesterDto {

	private Semester semester;
	private long totalCourse;

	public CoursesBySemesterDto(Semester semester, long totalCourse) {
		super();
		this.semester = semester;
		this.totalCourse = totalCourse;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public long getTotalCourse() {
		return totalCourse;
	}

	public void setTotalCourse(long totalCourse) {
		this.totalCourse = totalCourse;
	}

}