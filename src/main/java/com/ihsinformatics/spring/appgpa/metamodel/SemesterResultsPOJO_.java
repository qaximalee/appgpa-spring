package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SemesterResultsPOJO.class)
public abstract class SemesterResultsPOJO_ {

	public static volatile SingularAttribute<SemesterResultsPOJO, String> firstName;
	public static volatile SingularAttribute<SemesterResultsPOJO, String> lastName;
	public static volatile SingularAttribute<SemesterResultsPOJO, Double> semesterGPA;
	public static volatile SingularAttribute<SemesterResultsPOJO, String> registrationNo;
	public static volatile SingularAttribute<SemesterResultsPOJO, Integer> semesterResultsId;
	public static volatile SingularAttribute<SemesterResultsPOJO, Integer> semesterNo;
	public static volatile SingularAttribute<SemesterResultsPOJO, Double> cGPA;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String SEMESTER_GP_A = "semesterGPA";
	public static final String REGISTRATION_NO = "registrationNo";
	public static final String SEMESTER_RESULTS_ID = "semesterResultsId";
	public static final String SEMESTER_NO = "semesterNo";
	public static final String C_GP_A = "cGPA";

}
