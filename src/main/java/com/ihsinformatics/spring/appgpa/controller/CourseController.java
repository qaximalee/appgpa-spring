package com.ihsinformatics.spring.appgpa.controller;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	private static final String CREATED_SUCCESS = "from-create";
	private static final String CREATED_UNSUCCESS = "from-create-error";
	private static final String UPDATED_SUCCESS = "from-edit";
	private static final String UPDATED_UNSUCCESS = "from-edit-error";
	private static final String DELETED_SUCCESS = "from-delete";
	private static final String DELETED_UNSUCCESS = "from-delete-error";

	private static final String COURSE_VIEW_URL = "/course_views/view_courses";

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
			return viewCourses(null, CREATED_SUCCESS);
		else
			return viewCourses(null, CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editCourse", method = RequestMethod.POST)
	public ModelAndView editCourse(@RequestParam("courseId") int courseId, @RequestParam("courseCode") int courseCode,
			@RequestParam("semesterId") int semesterId, @RequestParam("name") String name) {
		Semester semester = semesterService.getSemesterById(semesterId);
		Course course = new Course(courseId, courseCode, name, semester);

		if (courseService.update(course))
			return viewCourses(null, UPDATED_SUCCESS);
		else
			return viewCourses(null, UPDATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editCourseForm", method = RequestMethod.GET)
	public ModelAndView editCourseForm(@RequestParam("id") int courseId) {
		ModelAndView mav = new ModelAndView("course_views/edit_course_form");
		mav.addObject("course", courseService.getSingle(courseId));
		mav.addObject("semesters", semesterService.getAllSemester());
		return mav;
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@RequestParam("id") int courseId) {

		if (courseService.delete(courseId))
			return viewCourses(null, DELETED_SUCCESS);
		else
			return viewCourses(null, DELETED_UNSUCCESS);
	}

	@RequestMapping(value = { "/", "/viewCourses" })
	public ModelAndView viewCourses(HttpServletRequest request, String alertMessageIdentifier) {
		if (request == null || request.getRequestURL().toString().contains("viewCourses")) {
			if (alertMessageIdentifier == null) {
				alertMessageIdentifier = "just-view";
				System.out.println(alertMessageIdentifier);
			}
			ModelAndView mav = new ModelAndView(COURSE_VIEW_URL);
			mav.addObject("courseList", courseService.getAll());
			mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("course_views/add_course_form");
			mav.addObject("semesterList", semesterService.getAllSemester());
			return mav;
		}
	}
}
