package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/semester-results")
public class SemesterResultsController {

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private StudentService studentService;

	/**
	 * This end point will generate the semester results by student id semester
	 * results.
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addSemesterResults", method = RequestMethod.POST)
	public ModelAndView addSemesterResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId) {

		SemesterResults semesterResults = semesterResultsService.calculateSemesterResults(studentId, semesterId);

		boolean isSemesterResultPresent = semesterResultsService.getSemesterResultsByStudentAndSemesterId(studentId,
				semesterId);

		if (isSemesterResultPresent) {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("semesterResults", semesterResultsService.getSemesterResultsByStudentId(studentId))
					.addObject("alertMessageIdentifier", "retrieve");
		} else if (semesterResultsService.save(semesterResults)) {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("alertMessageIdentifier", Values.CREATED_SUCCESS)
					.addObject("semesterResults", semesterResultsService.getSemesterResultsByStudentId(studentId));
		} else {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("alertMessageIdentifier", Values.CREATED_UNSUCCESS);
		}
	}

	/**
	 * It will show all semester results and take a string value by which can we
	 * decide the user created updated or deleted the semester results.
	 * 
	 * @param alertMessageIdentifier
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewSemesterResults", method = RequestMethod.GET)
	public ModelAndView viewSemesterResults(String alertMessageIdentifier) {
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(Values.SEMESTER_RESULTS_VIEW_URL);
		mav.addObject("semesterResults", semesterResultsService.getAllReadableSemesterResults());
		return mav;
	}

	/**
	 * It will show the semester results generation page.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		ModelAndView mav = new ModelAndView("/semester_results_views/add_semester_results_form");
		mav.addObject("semesterList", semesterService.getAllSemester());
		mav.addObject("studentList", studentService.getAllStudents());
		return mav;
	}
}