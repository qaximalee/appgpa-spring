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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

@RestController
@RequestMapping("rest-semesterResults")
public class SemesterResultsRestController {

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseResultsService courseResultsService;

	@GetMapping("/")
	public List<SemesterResultsPOJO> getAllSemesterResultsPOJO() {
		return this.semesterResultsService.getAllReadableSemesterResults();
	}

	@GetMapping("/all")
	public List<SemesterResults> getAllSemesterResults() {
		return this.semesterResultsService.getAllSemesterResults();
	}

	@PostMapping("/{id}")
	public SemesterResults getSingleSemesterResult(@PathVariable("id") int semesterResultId) {
		return this.semesterResultsService.getSemesterResultsById(semesterResultId);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addSemesterResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId) {
		Student student = studentService.getStudentById(studentId);
		Semester semester = semesterService.getSemesterById(semesterId);

		List<CourseResults> listOfCourseResults = courseResultsService
				.getCourseResultsByStudentAndSemesterId(semesterId, studentId);

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

		boolean isSemesterResultPresent = semesterResultsService.getSemesterResultsByStudentAndSemesterId(studentId,
				semesterId);

		if (isSemesterResultPresent) {
			return new ResponseEntity<>(new JSONObject()
					.put("semesterResults", semesterResultsService.getSemesterResultsByStudentId(studentId)).toString(),
					HttpStatus.OK);
		} else if (semesterResultsService.save(semesterResults)) {
			return new ResponseEntity<>(new JSONObject()
					.put("semesterResults", semesterResultsService.getSemesterResultsByStudentId(studentId)).toString(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new JSONObject().put("error", "semesterResults is not added").toString(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<Object> deleteSemesterResult(@PathVariable("id") int semesterResultId) {
		if (this.semesterResultsService.deleteSemesterResultsById(semesterResultId))
			return new ResponseEntity<>(this.semesterResultsService.getSemesterResultsById(semesterResultId).toString(),
					HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "semesterResults is not deleted").toString(),
					HttpStatus.BAD_REQUEST);
	}

}
