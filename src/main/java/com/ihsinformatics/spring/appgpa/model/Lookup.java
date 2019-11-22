package com.ihsinformatics.spring.appgpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lookup")
public class Lookup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lookup_id")
	private int lookupId;

	@Column(name = "grade")
	private String grade;

	@Column(name = "start_percentage")
	private double startParcentage;

	@Column(name = "end_percentage")
	private double endPercentage;

	@Column(name = "gpa")
	private double gpa;

	public Lookup() {
	}

	public Lookup(int lookupId, String grade, double startParcentage, double endPercentage, double gpa) {
		super();
		this.lookupId = lookupId;
		this.grade = grade;
		this.startParcentage = startParcentage;
		this.endPercentage = endPercentage;
		this.gpa = gpa;
	}

	public int getLookupId() {
		return lookupId;
	}

	public void setLookupId(int lookupId) {
		this.lookupId = lookupId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getStartParcentage() {
		return startParcentage;
	}

	public void setStartParcentage(double startParcentage) {
		this.startParcentage = startParcentage;
	}

	public double getEndPercentage() {
		return endPercentage;
	}

	public void setEndPercentage(double endPercentage) {
		this.endPercentage = endPercentage;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

}
