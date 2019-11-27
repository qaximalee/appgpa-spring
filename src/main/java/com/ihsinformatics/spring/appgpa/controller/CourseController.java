package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.imp.CourseServiceImp;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	public void setCourseService(CourseServiceImp courseService) {
		this.courseService = courseService;
	}

	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public ModelAndView addCourse(@RequestParam("courseCode") int courseCode, @RequestParam("semester") int semesterId,
			@RequestParam("name") String name) {
		Semester semester = semesterService.getSemesterById(semesterId);
		Course course = new Course(0, courseCode, name, semester);

		if (courseService.save(course))
			return viewCourses(Values.CREATED_SUCCESS);
		else
			return viewCourses(Values.CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editCourse", method = RequestMethod.POST)
	public ModelAndView editCourse(@RequestParam("courseId") int courseId, @RequestParam("courseCode") int courseCode,
			@RequestParam("semesterId") int semesterId, @RequestParam("name") String name) {
		Semester semester = semesterService.getSemesterById(semesterId);
		Course course = new Course(courseId, courseCode, name, semester);

		if (courseService.update(course))
			return viewCourses(Values.UPDATED_SUCCESS);
		else
			return viewCourses(Values.UPDATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editCourseForm", method = RequestMethod.GET)
	public ModelAndView editCourseForm(@RequestParam("id") int courseId) {
		ModelAndView mav = new ModelAndView("course_views/edit_course_form");
		mav.addObject("course", courseService.getCourseById(courseId));
		mav.addObject("semesters", semesterService.getAllSemester());
		return mav;
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@RequestParam("id") int courseId) {

		if (courseService.deleteCourseById(courseId))
			return viewCourses(Values.DELETED_SUCCESS);
		else
			return viewCourses(Values.DELETED_UNSUCCESS);
	}

	@RequestMapping(value = "/viewCourses")
	public ModelAndView viewCourses(String alertMessageIdentifier) {
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(Values.COURSE_VIEW_URL);
		mav.addObject("courseList", courseService.getAllCourses());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		ModelAndView mav = new ModelAndView("course_views/add_course_form");
		mav.addObject("semesterList", semesterService.getAllSemester());
		return mav;
	}

}
