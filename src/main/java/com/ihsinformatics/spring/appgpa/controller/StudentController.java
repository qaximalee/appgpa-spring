package com.ihsinformatics.spring.appgpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.service.imp.StudentServiceImp;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	public void setStudentService(StudentServiceImp studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/add_students", method = RequestMethod.GET)
	public String addStudent() {
		return "student_views/add_student_form";
	}

	@RequestMapping(value = "/getAllStudents")
	public void viewStudents() {
		List<Student> studentList = this.studentService.getAll();
		for (Student student : studentList)
			System.out.println("NAME: " + student.getFirstName() + student.getLastName());
	}

}
