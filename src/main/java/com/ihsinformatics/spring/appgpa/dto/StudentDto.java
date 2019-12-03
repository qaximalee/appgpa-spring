package com.ihsinformatics.spring.appgpa.dto;

public class StudentDto {

	private String firstName;
	private String lastName;
	private String registrationNo;
	private double cgpa;
	private int semesterNo;
	private int totalNoOfCourses;

	public StudentDto(String firstName, String lastName, String registrationNo, double cgpa, int semesterNo,
			int totalNoOfCourses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationNo = registrationNo;
		this.cgpa = cgpa;
		this.semesterNo = semesterNo;
		this.totalNoOfCourses = totalNoOfCourses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public int getSemesterNo() {
		return semesterNo;
	}

	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}

	public int getTotalNoOfCourses() {
		return totalNoOfCourses;
	}

	public void setTotalNoOfCourses(int totalNoOfCourses) {
		this.totalNoOfCourses = totalNoOfCourses;
	}
}
