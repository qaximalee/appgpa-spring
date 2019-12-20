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

import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

@RestController
@RequestMapping("rest-semesterResults")
public class SemesterResultsRestController {

	@Autowired
	private SemesterResultsService semesterResultsService;

	/**
	 * This end point will return all semester results in a readable form. It will
	 * not return semester or student id but return student's info.
	 * 
	 * @return List<SemesterResultsPOJO>
	 */
	@GetMapping("/readableList")
	public List<SemesterResultsPOJO> getAllSemesterResultsPOJO() {
		return this.semesterResultsService.getAllReadableSemesterResults();
	}

	/**
	 * This end point will return all semester results in a non readable form i.e it
	 * will return semester's id, student's id.
	 * 
	 * @return List<SemesterResults>
	 */
	@GetMapping("/list")
	public List<SemesterResults> getAllSemesterResults() {
		return this.semesterResultsService.getAllSemesterResults();
	}

	/**
	 * This end point will return a single semester result by it's id.
	 * 
	 * @return SemesterResults
	 */
	@PostMapping("/{id}")
	public SemesterResults getSingleSemesterResult(@PathVariable("id") int semesterResultId) {
		return this.semesterResultsService.getSemesterResultsById(semesterResultId);
	}

	/**
	 * This end point will generate the semester results by student id and there
	 * semester id. results.
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addSemesterResults(@RequestParam("studentId") int studentId,
			@RequestParam("semesterId") int semesterId) {
		SemesterResults semesterResults = semesterResultsService.calculateSemesterResults(studentId, semesterId);

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

	/**
	 * This will delete a semester result by it's semester results's id.
	 * 
	 * @param semestesResultId
	 * @return ResponseEntity<Object>
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteSemesterResult(@PathVariable("id") int semesterResultId) {
		if (this.semesterResultsService.deleteSemesterResultsById(semesterResultId))
			return new ResponseEntity<>(this.semesterResultsService.getSemesterResultsById(semesterResultId).toString(),
					HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "semesterResults is not deleted").toString(),
					HttpStatus.BAD_REQUEST);
	}

}
