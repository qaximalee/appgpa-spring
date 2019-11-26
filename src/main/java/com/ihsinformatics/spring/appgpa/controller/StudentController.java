package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.service.imp.StudentServiceImp;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	private static final String CREATED_SUCCESS = "from-create";
	private static final String CREATED_UNSUCCESS = "from-create-error";
	private static final String UPDATED_SUCCESS = "from-edit";
	private static final String UPDATED_UNSUCCESS = "from-edit-error";
	private static final String DELETED_SUCCESS = "from-delete";
	private static final String DELETED_UNSUCCESS = "from-delete-error";

	private static final String STUDENT_VIEW_URL = "/student_views/view_students";

	public void setStudentService(StudentServiceImp studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("registrationNo") String registrationNo) {

		Student student = new Student(0, registrationNo, firstName, lastName);
		if (studentService.save(student))
			return viewStudent(CREATED_SUCCESS);
		else
			return viewStudent(CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public ModelAndView editStudent(@RequestParam("studentId") int studentId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("registrationNo") String registrationNo) {
		Student student = new Student(studentId, registrationNo, firstName, lastName);

		if (studentService.update(student))
			return viewStudent(UPDATED_SUCCESS);
		else
			return viewStudent(UPDATED_UNSUCCESS);
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
			return viewStudent(DELETED_SUCCESS);
		else
			return viewStudent(DELETED_UNSUCCESS);
	}

	@RequestMapping(value = "/viewStudents", method = RequestMethod.GET)
	public ModelAndView viewStudent(String alertMessageIdentifier) {
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(STUDENT_VIEW_URL);
		mav.addObject("data", studentService.getAllStudents());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;
	}

}
