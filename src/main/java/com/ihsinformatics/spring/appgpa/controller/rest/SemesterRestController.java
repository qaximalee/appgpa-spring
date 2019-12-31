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

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;

@RestController
@RequestMapping("/rest-semester")
public class SemesterRestController {

	@Autowired
	private SemesterService semesterService;

	/**
	 * This end point return all Semester's list for the front end view.
	 * 
	 * @return List<Semester"
	 */
	@GetMapping("/list")
	public List<Semester> getAllSemester() {
		List<Semester> semesters = this.semesterService.getAllSemesters();
		return semesters;
	}

	/**
	 * This end point will give single semester by it's semesterId.
	 * 
	 * @param semesterId
	 * @return Semester
	 */
	@PostMapping("/{id}")
	public Semester getSingleSemester(@PathVariable("id") int semesterId) {
		Semester semester = this.semesterService.getSemesterById(semesterId);
		return semester;
	}

	/**
	 * This will add a semester.
	 * 
	 * @param semesterNo
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addSemester(@RequestParam("semesterNo") int semesterNo) {

		Semester semester = new Semester(0, semesterNo);

		if (semesterService.save(semester))
			return new ResponseEntity<>(semester, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "semester is not added"), HttpStatus.BAD_REQUEST);

	}

	/**
	 * This end point will get Response Body of Semester for the update.
	 * 
	 * @param ResponseBody
	 *            Semester
	 * @return ResponseEntity<Object>
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<Object> editSemester(@Validated @RequestBody Semester semester) {

		if (semesterService.update(semester))
			return new ResponseEntity<>(semester, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "Semester isn't present in database").toString(),
					HttpStatus.BAD_REQUEST);
	}

	/**
	 * This will delete the semester with respect to it's semesterId.
	 * 
	 * @param semesterId
	 * @return ResponseEntity<Object>
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteSemester(@PathVariable("id") int semesterId) {
		Semester semester = this.semesterService.getSemesterById(semesterId);

		if (semesterService.deleteSemesterById(semesterId))
			return new ResponseEntity<>(semester, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "semester is not deleted").toString(),
					HttpStatus.BAD_REQUEST);
	}
}
