package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.Semester;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Semester.class)
public abstract class Semester_ {

	public static volatile SingularAttribute<Semester, Integer> semesterId;
	public static volatile SingularAttribute<Semester, Integer> semesterNo;

	public static final String SEMESTER_ID = "semesterId";
	public static final String SEMESTER_NO = "semesterNo";

}
