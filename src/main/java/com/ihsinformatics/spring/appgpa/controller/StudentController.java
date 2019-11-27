package com.ihsinformatics.spring.appgpa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.service.imp.StudentServiceImp;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	public void setStudentService(StudentServiceImp studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("registrationNo") String registrationNo) {

		Student student = new Student(0, registrationNo, firstName, lastName);
		if (studentService.save(student))
			return viewStudent(null, Values.CREATED_SUCCESS);
		else
			return viewStudent(null, Values.CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public ModelAndView editStudent(@RequestParam("studentId") int studentId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("registrationNo") String registrationNo) {
		Student student = new Student(studentId, registrationNo, firstName, lastName);

		if (studentService.update(student))
			return viewStudent(null, Values.UPDATED_SUCCESS);
		else
			return viewStudent(null, Values.UPDATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editStudentForm", method = RequestMethod.GET)
	public ModelAndView editStudent(@RequestParam("id") int studentId) {
		ModelAndView mav = new ModelAndView("student_views/edit_student_form");
		mav.addObject("student", studentService.getStudentById(studentId));
		return mav;
	}

	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@RequestParam("id") int studentId) {

		if (studentService.deleteStudentById(studentId))
			return viewStudent(null, Values.DELETED_SUCCESS);
		else
			return viewStudent(null, Values.DELETED_UNSUCCESS);
	}

	@RequestMapping(value = { "/", "/viewStudents" })
	public ModelAndView viewStudent(HttpServletRequest request, String alertMessageIdentifier) {
		if (request == null || request.getRequestURL().toString().contains("viewStudents")) {
			if (alertMessageIdentifier == null) {
				alertMessageIdentifier = "just-view";
				System.out.println(alertMessageIdentifier);
			}
			ModelAndView mav = new ModelAndView(Values.STUDENT_VIEW_URL);
			mav.addObject("data", studentService.getAllStudents());
			mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
			return mav;
		} else {
			return new ModelAndView("student_views/add_student_form");
		}
	}

}
