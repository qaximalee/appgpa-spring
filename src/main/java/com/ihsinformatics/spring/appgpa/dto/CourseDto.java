package com.ihsinformatics.spring.appgpa.dto;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Student;

public class CourseDto {

	private Course course;
	private double marks;
	private Student students;

	public CourseDto(Course course, double marks, Student students) {
		super();
		this.course = course;
		this.marks = marks;
		this.students = students;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public Student getStudents() {
		return students;
	}

	public void setStudents(Student students) {
		this.students = students;
	}
}
