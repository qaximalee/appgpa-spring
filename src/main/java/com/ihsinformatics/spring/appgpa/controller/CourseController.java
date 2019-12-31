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
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	/**
	 * This end point will add a course.
	 * 
	 * @param int:
	 *            courseCode of the course
	 * @param int:
	 *            semesterId of the course
	 * @param String:
	 *            name of the course
	 * 
	 * @return ModelAndView
	 */
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

	/**
	 * This end point will update a course.
	 * 
	 * @param int:
	 *            courseId of the course
	 * @param int:
	 *            courseCode of the course
	 * @param int:
	 *            semesterId of the course
	 * @param String:
	 *            name of the course
	 * 
	 * @return ModelAndView
	 */
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

	/**
	 * This end point will show the edit view of the Course.
	 * 
	 * @param int:
	 *            course id of that course which you want to update
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editCourseForm", method = RequestMethod.GET)
	public ModelAndView editCourseForm(@RequestParam("id") int courseId) {
		ModelAndView mav = new ModelAndView("course_views/edit_course_form");
		mav.addObject("course", courseService.getCourseById(courseId));
		mav.addObject("semesters", semesterService.getAllSemesters());
		return mav;
	}

	/**
	 * This end point will delete a course.
	 * 
	 * @param courseId
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@RequestParam("id") int courseId) {

		if (courseService.deleteCourseById(courseId))
			return viewCourses(Values.DELETED_SUCCESS);
		else
			return viewCourses(Values.DELETED_UNSUCCESS);
	}

	/**
	 * This method will show All the courses and a parameter will be taken which is
	 * used to identify a user has updated, added or deleted a course.
	 * 
	 * @param Stirng:
	 *            alertMessageIdentifier
	 * 
	 * @return ModelAndView
	 */
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

	/**
	 * This end point will show the Main page of the Course.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		ModelAndView mav = new ModelAndView("course_views/add_course_form");
		mav.addObject("semesterList", semesterService.getAllSemesters());
		return mav;
	}

}
