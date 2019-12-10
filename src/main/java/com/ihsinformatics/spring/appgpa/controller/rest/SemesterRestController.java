package com.ihsinformatics.spring.appgpa.controller.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.validation.Validate;

@RestController
@RequestMapping("/rest-semester")
public class SemesterRestController {

	@Autowired
	private SemesterService semesterService;

	@GetMapping("/")
	public List<Semester> getAllSemester() {
		List<Semester> semesters = this.semesterService.getAllSemester();
		return semesters;
	}

	@PostMapping("/{id}")
	public Semester getSingleSemester(@PathVariable("id") int semesterId) {
		Semester semester = this.semesterService.getSemesterById(semesterId);
		return semester;
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addSemester(@RequestParam("semesterNo") int semesterNo) {
		JSONObject jsonValidation = new JSONObject();

		if (!Validate.isValidSemesterNo(semesterNo))
			jsonValidation.put("error", "invalid semester no");

		Semester semester = new Semester(0, semesterNo);

		if (jsonValidation.isEmpty()) {
			if (semesterService.save(semester))
				return new ResponseEntity<>(semester, HttpStatus.OK);
			else
				return new ResponseEntity<>(new JSONObject().put("error", "semester is not added"),
						HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(jsonValidation.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Object> editSemester(@RequestParam("semesterId") int semesterId,
			@RequestParam("semesterNo") int semesterNo) {
		JSONObject jsonValidation = new JSONObject();

		if (!Validate.isValidSemesterNo(semesterNo))
			jsonValidation.put("error", "invalid semester no");

		Semester semester = new Semester(semesterId, semesterNo);

		if (jsonValidation.isEmpty()) {
			if (semesterService.update(semester))
				return new ResponseEntity<>(semester, HttpStatus.OK);
			else
				return new ResponseEntity<>(new JSONObject().put("error", "semester is not updated").toString(),
						HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(jsonValidation.toString().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<Object> deleteSemester(@PathVariable("id") int semesterId) {
		Semester semester = this.semesterService.getSemesterById(semesterId);

		if (semesterService.deleteSemesterById(semesterId))
			return new ResponseEntity<>(semester, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "semester is not deleted").toString(),
					HttpStatus.BAD_REQUEST);
	}
}
