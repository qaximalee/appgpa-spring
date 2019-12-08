package com.ihsinformatics.spring.appgpa.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.ihsinformatics.spring.appgpa.model.Lookup;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lookup.class)
public abstract class Lookup_ {

	public static volatile SingularAttribute<Lookup, Integer> lookupId;
	public static volatile SingularAttribute<Lookup, String> grade;
	public static volatile SingularAttribute<Lookup, Double> startParcentage;
	public static volatile SingularAttribute<Lookup, Double> endPercentage;
	public static volatile SingularAttribute<Lookup, Double> gpa;

	public static final String LOOKUP_ID = "lookupId";
	public static final String GRADE = "grade";
	public static final String START_PARCENTAGE = "startParcentage";
	public static final String END_PERCENTAGE = "endPercentage";
	public static final String GPA = "gpa";

}
