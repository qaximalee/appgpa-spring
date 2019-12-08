package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.CourseResultsPOJO;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourseResultsPOJO.class)
public abstract class CourseResultsPOJO_ {

	public static volatile SingularAttribute<CourseResultsPOJO, String> firstName;
	public static volatile SingularAttribute<CourseResultsPOJO, String> lastName;
	public static volatile SingularAttribute<CourseResultsPOJO, Integer> courseResultId;
	public static volatile SingularAttribute<CourseResultsPOJO, String> courseName;
	public static volatile SingularAttribute<CourseResultsPOJO, String> registrationNo;
	public static volatile SingularAttribute<CourseResultsPOJO, Double> percentage;
	public static volatile SingularAttribute<CourseResultsPOJO, String> grade;
	public static volatile SingularAttribute<CourseResultsPOJO, Double> totalPoints;
	public static volatile SingularAttribute<CourseResultsPOJO, Double> gpa;
	public static volatile SingularAttribute<CourseResultsPOJO, Integer> semesterNo;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String COURSE_RESULT_ID = "courseResultId";
	public static final String COURSE_NAME = "courseName";
	public static final String REGISTRATION_NO = "registrationNo";
	public static final String PERCENTAGE = "percentage";
	public static final String GRADE = "grade";
	public static final String TOTAL_POINTS = "totalPoints";
	public static final String GPA = "gpa";
	public static final String SEMESTER_NO = "semesterNo";

}
