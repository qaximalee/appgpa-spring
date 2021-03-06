package com.ihsinformatics.spring.appgpa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Lookup;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.LookupService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.service.imp.CourseResultsServiceImp;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/course-results")
public class CourseResultsController {

	@Autowired
	private CourseResultsService courseResultsService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private LookupService lookupService;

	public void setCourseResultsService(CourseResultsServiceImp courseResultsService) {
		this.courseResultsService = courseResultsService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	public void setLookupService(LookupService lookupService) {
		this.lookupService = lookupService;
	}

	@RequestMapping(value = "/addCourseResults", method = RequestMethod.POST)
	public ModelAndView addCourseResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId, @RequestParam("courseId") int courseId,
			@RequestParam("percentage") double percentage) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);

		StringBuilder grade = new StringBuilder();
		double gpa = 0.0;

		List<Lookup> listOfLookup = lookupService.getAllLookup();
		for (Lookup lookup : listOfLookup) {
			if (percentage >= lookup.getStartParcentage() && percentage <= lookup.getEndPercentage()) {
				gpa = lookup.getGpa();
				grade.append(lookup.getGrade());
			}
		}

		// GPA Multiply by 3 (ie 3 is credit hours)
		double totalPoints = gpa * 3;
		CourseResults courseResults = new CourseResults(0, course, student, percentage, gpa, grade.toString(),
				totalPoints);
		grade.replace(0, grade.length(), "");
		if (courseResultsService.save(courseResults))
			return courseResultsView(Values.CREATED_SUCCESS);
		else
			return courseResultsView(Values.CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/getCoursesBySemester", method = RequestMethod.GET)
	public void getCoursesBySemester(HttpServletResponse response, @RequestParam("semesterID") int semesterId) {
		List<Course> courses = courseService.getCoursesBySemesterId(semesterId);
		JSONArray courseJson = new JSONArray(courses);
		try {
			response.getWriter().print(courseJson.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getStudentByRegistration", method = RequestMethod.GET)
	public void getStudentByRegistration(HttpServletResponse response, @RequestParam("studentID") int studentId) {
		Student students = studentService.getStudentById(studentId);
		JSONObject studentJson = new JSONObject(students);
		try {
			response.getWriter().print(studentJson.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/deleteCourseResult", method = RequestMethod.GET)
	public ModelAndView deleteCourseResult(@RequestParam("id") int courseResultsId) {
		if (courseResultsService.deleteCourseResultsById(courseResultsId))
			return courseResultsView(Values.DELETED_SUCCESS);
		else
			return courseResultsView(Values.DELETED_UNSUCCESS);
	}

	@RequestMapping(value = "/viewCourseResults")
	public ModelAndView courseResultsView(String alertMessageIdentifier) {
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(Values.COURSE_RESULTS_VIEW_URL);
		mav.addObject("courseResultsList", courseResultsService.getAllReadableCourseResults());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		ModelAndView mav = new ModelAndView("course_results_views/add_course_results_form");
		mav.addObject("semesterList", semesterService.getAllSemester());
		mav.addObject("courseList", courseService.getAllCourses());
		mav.addObject("studentList", studentService.getAllStudents());
		return mav;
	}
}
