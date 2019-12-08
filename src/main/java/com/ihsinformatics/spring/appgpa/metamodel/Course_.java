package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Semester;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Course.class)
public abstract class Course_ {

	public static volatile SingularAttribute<Course, Integer> courseCode;
	public static volatile SingularAttribute<Course, String> name;
	public static volatile SingularAttribute<Course, Semester> semester;
	public static volatile SingularAttribute<Course, Integer> courseId;

	public static final String COURSE_CODE = "courseCode";
	public static final String NAME = "name";
	public static final String SEMESTER = "semester";
	public static final String COURSE_ID = "courseId";

}
