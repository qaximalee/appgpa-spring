package com.ihsinformatics.spring.appgpa.dto;

import com.ihsinformatics.spring.appgpa.model.Student;

public class StudentSemesterDto {
	private Student student;
	private long currentSemester;

	public StudentSemesterDto(Student student, long currentSemester) {
		super();
		this.student = student;
		this.currentSemester = currentSemester;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(long currentSemester) {
		this.currentSemester = currentSemester;
	}

}
