package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.Student;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SemesterResults.class)
public abstract class SemesterResults_ {

	public static volatile SingularAttribute<SemesterResults, Double> semesterGPA;
	public static volatile SingularAttribute<SemesterResults, Student> student;
	public static volatile SingularAttribute<SemesterResults, Semester> semester;
	public static volatile SingularAttribute<SemesterResults, Double> cGPA;
	public static volatile SingularAttribute<SemesterResults, Integer> semesterResultId;

	public static final String SEMESTER_GP_A = "semesterGPA";
	public static final String STUDENT = "student";
	public static final String SEMESTER = "semester";
	public static final String C_GP_A = "cGPA";
	public static final String SEMESTER_RESULT_ID = "semesterResultId";

}
