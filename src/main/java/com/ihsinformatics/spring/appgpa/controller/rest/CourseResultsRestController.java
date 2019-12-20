package com.ihsinformatics.spring.appgpa.controller.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.CourseService;
import com.ihsinformatics.spring.appgpa.service.LookupService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@RestController
@RequestMapping("/rest-courseResults")
public class CourseResultsRestController {

	@Autowired
	private CourseResultsService courseResultsService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private LookupService lookupService;

	/**
	 * Get All CourseResults in database.
	 */
	@GetMapping("/list")
	public List<CourseResults> getAllCourseResults() {
		List<CourseResults> courseResults = this.courseResultsService.getAllCourseResults();
		return courseResults;
	}

	/**
	 * Get a CourseResult by it's id
	 */
	@PostMapping("/{id}")
	public CourseResults getSingleCourseResult(@PathVariable("id") int id) {
		CourseResults courseResult = this.courseResultsService.getCourseResultsById(id);
		return courseResult;
	}

	/**
	 * HTTP: http:localhost:8080/appgpa-spring/rest-courseResults/add. This will add
	 * Course Results and return ResponseEntity with CourseResults if it is added
	 * successfully other wise it will generate HttpStatus BAD null value.
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<Object> addCourseResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId, @RequestParam("courseId") int courseId,
			@RequestParam("percentage") double percentage) {

		CourseResults courseResults = courseResultsService.calculateCourseResult(studentId, semesterId, courseId,
				percentage);
		if (courseResultsService.save(courseResults))
			return new ResponseEntity<>(courseResults, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "courseResults is not added"), HttpStatus.OK);
	}

	/**
	 * Delete a CourseResults by it's id. If deletion is success then it will
	 * generate RESPONSE OK or RESPONSE BAD
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CourseResults> deleteCourseResult(@PathVariable("id") int id) {
		CourseResults courseResult = getSingleCourseResult(id);
		if (this.courseResultsService.deleteCourseResultsById(id))
			return new ResponseEntity<>(courseResult, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}
