package com.ihsinformatics.spring.appgpa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@Controller
@RequestMapping("/semester-results")
public class SemesterResultsController {

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseResultsService courseResultsService;

	private static final String CREATED_SUCCESS = "from-create";
	private static final String CREATED_UNSUCCESS = "from-create-error";
	private static final String UPDATED_SUCCESS = "from-edit";
	private static final String UPDATED_UNSUCCESS = "from-edit-error";
	private static final String DELETED_SUCCESS = "from-delete";
	private static final String DELETED_UNSUCCESS = "from-delete-error";

	private static final String SEMESTER_RESULTS_VIEW_URL = "/semester_results_views/view_semester_results";

	public void setSemesterResultsService(SemesterResultsService semesterResultsService) {
		this.semesterResultsService = semesterResultsService;
	}

	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setCourseResultsService(CourseResultsService courseResultsService) {
		this.courseResultsService = courseResultsService;
	}

	@RequestMapping(value = "/addSemesterResults", method = RequestMethod.POST)
	public ModelAndView addSemesterResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId) {
		Student student = studentService.getStudentById(studentId);
		Semester semester = semesterService.getSemesterById(semesterId);

		List<CourseResults> listOfCourseResults = courseResultsService.getAllCourseResultsBySemester(semesterId,
				studentId);

		// Semester GPA can be get by below formula
		// gpa = totalPoints / gradableCredit
		// where,
		// totalPoints = gpa * credit hours
		// gradableCredit = total credit hours
		double totalPoints = 0.0;

		for (CourseResults courseResults : listOfCourseResults) {
			totalPoints += courseResults.getTotalPoints();
		}

		double gradableCredit = 3 * listOfCourseResults.size();
		double semesterGPA = totalPoints / gradableCredit;

		SemesterResults semesterResults = new SemesterResults(0, semester, student, semesterGPA, semesterGPA);

		boolean isSemesterResultPresent = semesterResultsService.getSemesterResultsBy(studentId, semesterId);

		if (isSemesterResultPresent) {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("semesterResults", semesterResultsService.getStudentSemResults(studentId))
					.addObject("alertMessageIdentifier", "retrieve");
		} else if (semesterResultsService.save(semesterResults)) {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("alertMessageIdentifier", CREATED_SUCCESS)
					.addObject("semesterResults", semesterResultsService.getStudentSemResults(studentId));
		} else {
			return new ModelAndView("semester_results_views/view_std_semester_results")
					.addObject("alertMessageIdentifier", CREATED_UNSUCCESS);
		}
	}

	@RequestMapping(value = { "/", "/viewSemesterResults" }, method = RequestMethod.GET)
	public ModelAndView viewSemesterResults(HttpServletRequest request, String alertMessageIdentifier) {
		if (request == null || request.getRequestURL().toString().contains("viewSemesterResults")) {
			if (alertMessageIdentifier == null) {
				alertMessageIdentifier = "just-view";
				System.out.println(alertMessageIdentifier);
			}
			ModelAndView mav = new ModelAndView(SEMESTER_RESULTS_VIEW_URL);
			mav.addObject("semesterResults", semesterResultsService.getAllReadableResults());
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("/semester_results_views/add_semester_results_form");
			mav.addObject("semesterList", semesterService.getAllSemester());
			mav.addObject("studentList", studentService.getAllStudents());
			return mav;
		}
	}
}