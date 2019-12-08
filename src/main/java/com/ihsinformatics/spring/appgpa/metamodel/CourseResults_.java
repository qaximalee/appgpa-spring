package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Student;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourseResults.class)
public abstract class CourseResults_ {

	public static volatile SingularAttribute<CourseResults, Integer> courseResultId;
	public static volatile SingularAttribute<CourseResults, Student> student;
	public static volatile SingularAttribute<CourseResults, Double> percentage;
	public static volatile SingularAttribute<CourseResults, String> grade;
	public static volatile SingularAttribute<CourseResults, Double> totalPoints;
	public static volatile SingularAttribute<CourseResults, Course> course;
	public static volatile SingularAttribute<CourseResults, Double> gpa;

	public static final String COURSE_RESULT_ID = "courseResultId";
	public static final String STUDENT = "student";
	public static final String PERCENTAGE = "percentage";
	public static final String GRADE = "grade";
	public static final String TOTAL_POINTS = "totalPoints";
	public static final String COURSE = "course";
	public static final String GPA = "gpa";

}
