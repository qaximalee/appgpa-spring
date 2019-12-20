package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * This end point will add a student.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param registrationNo
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("registrationNo") String registrationNo) {

		Student student = new Student(0, registrationNo, firstName, lastName);
		if (studentService.save(student))
			return viewStudent(Values.CREATED_SUCCESS);
		else
			return viewStudent(Values.CREATED_UNSUCCESS);
	}

	/**
	 * This end point will update a student by taking it's id.
	 * 
	 * @param studentId
	 * @param firstName
	 * @param lastName
	 * @param registrationNo
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public ModelAndView editStudent(@RequestParam("studentId") int studentId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("registrationNo") String registrationNo) {
		Student student = new Student(studentId, registrationNo, firstName, lastName);

		if (studentService.update(student))
			return viewStudent(Values.UPDATED_SUCCESS);
		else
			return viewStudent(Values.UPDATED_UNSUCCESS);
	}

	/**
	 * This end point will show the student edit form where user can edit student
	 * details.
	 * 
	 * @param studentId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editStudentForm", method = RequestMethod.GET)
	public ModelAndView editStudent(@RequestParam("id") int studentId) {
		ModelAndView mav = new ModelAndView("student_views/edit_student_form");
		mav.addObject("student", studentService.getStudentById(studentId));
		return mav;
	}

	/**
	 * This end point will delete a student by there student id.
	 * 
	 * @param studentId
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@RequestParam("id") int studentId) {

		if (studentService.deleteStudentById(studentId))
			return viewStudent(Values.DELETED_SUCCESS);
		else
			return viewStudent(Values.DELETED_UNSUCCESS);
	}

	/**
	 * This end point will show all the student's detail on web it will take a
	 * string value by which we can decide the user have added, updated or deleted a
	 * student.
	 * 
	 * @param alertMessageIdentifier
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewStudents")
	public ModelAndView viewStudent(String alertMessageIdentifier) {

		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(Values.STUDENT_VIEW_URL);
		mav.addObject("data", studentService.getAllStudents());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;
	}

	/**
	 * This will show the student's add form from which user can add students.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		return new ModelAndView("student_views/add_student_form");
	}

}
