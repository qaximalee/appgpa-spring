package com.ihsinformatics.spring.appgpa.controller;

import com.ihsinformatics.spring.appgpa.model.Student;

public class ClassVariables {
	public static void main(String[] args) {
		Object obj = new Student();
		java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
			System.out.println("Field " + i + ": " + fields[i]);
	}
}
