package com.ihsinformatics.spring.appgpa.controller.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.Course;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;

@RestController
@RequestMapping("/rest-course")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-course/ This end-point will
	 * RETRIEVE ALL Courses.
	 * 
	 * @return List<Course>
	 */
	@GetMapping("/list")
	public List<Course> getAllCourses() {

		List<Course> courses = this.courseService.getAllCourses();

		return courses;
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-course/{courseID} This
	 * end-point will RETRIEVE SINGLE Course.
	 * 
	 * @return Course
	 */
	@PostMapping("/{id}")
	public Course getSingleCourse(@PathVariable("id") int id) {
		Course course = this.courseService.getCourseById(id);
		return course;
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-course/add This end-point will
	 * CREATE Course.
	 * 
	 * @return EntityResponse<Object> of Course or Error message
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestParam("courseCode") int courseCode,
			@RequestParam("semesterId") int semesterId, @RequestParam("name") String name) {

		Semester semester = semesterService.getSemesterById(semesterId);
		Course course = new Course(0, courseCode, name, semester);

		if (courseService.save(course))
			return new ResponseEntity<>(course, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "course is not added"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-course/update This end-point
	 * will UPDATE Course.
	 * 
	 * @return EntityResponse<Object> of Course or Error message
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> editCourse(@Validated @RequestBody Course course) {
		if (courseService.update(course))
			return new ResponseEntity<>(course, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "Course is not Found"), HttpStatus.NOT_FOUND);
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-course/delete/{courseID} This
	 * end-point will DELETE Course.
	 * 
	 * @return EntityResponse<Object> of Course or Error message
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable("id") int courseId) {
		Course course = this.courseService.getCourseById(courseId);
		if (courseService.deleteCourseById(courseId))
			return new ResponseEntity<>(course, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "course is not deleted"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * For getting all courses in a semester;
	 * 
	 * @param semesterId
	 * @return List<Course>
	 */
	@PostMapping("/getCoursesBySemester/{semesterId}")
	public List<Course> getCoursesBySemester(@PathVariable("semesterId") int semesterId) {
		List<Course> courses = this.courseService.getCoursesBySemesterId(semesterId);
		return courses;
	}
}
