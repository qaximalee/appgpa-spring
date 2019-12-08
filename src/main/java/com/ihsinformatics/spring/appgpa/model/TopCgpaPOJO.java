package com.ihsinformatics.spring.appgpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TopCgpaPOJO {

	@Id
	@Column(name = "semester_results_id")
	private int semesterResultId;

	@OneToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@Column(name = "semester_gpa")
	private double semesterGPA;

	@Column(name = "cgpa")
	private double cGpa;

	public TopCgpaPOJO() {
	}

	public TopCgpaPOJO(int semesterResultId, Semester semester, Student student, double semesterGPA, double cGpa) {
		super();
		this.semesterResultId = semesterResultId;
		this.semester = semester;
		this.student = student;
		this.semesterGPA = semesterGPA;
		this.cGpa = cGpa;
	}

	public int getSemesterResultId() {
		return semesterResultId;
	}

	public void setSemesterResultId(int semesterResultId) {
		this.semesterResultId = semesterResultId;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getSemesterGPA() {
		return semesterGPA;
	}

	public void setSemesterGPA(double semesterGPA) {
		this.semesterGPA = semesterGPA;
	}

	public double getcGpa() {
		return cGpa;
	}

	public void setcGpa(double cGpa) {
		this.cGpa = cGpa;
	}

}
