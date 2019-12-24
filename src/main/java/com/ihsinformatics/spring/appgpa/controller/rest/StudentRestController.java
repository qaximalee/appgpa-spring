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

import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.StudentService;
import com.ihsinformatics.spring.appgpa.values.RestValues;

@RestController
@RequestMapping("/rest-student")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	/**
	 * Get All Students in database.
	 */
	@GetMapping("/list")
	public List<Student> getAllStudents() {

		List<Student> studentList = this.studentService.getAllStudents();

		return studentList;
	}

	/**
	 * Get a Student by it's id
	 */
	@PostMapping("/{id}")
	public Student getSingleStudent(@PathVariable("id") int id) {
		Student student = this.studentService.getStudentById(id);
		return student;
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-student/addStudent Parameters
	 * { registrationNo i.e EP-1234567 firstName i.e Muhammad lastName i.e Ali or
	 * Noor-u } This end-point will CREATE Student by validating above parameter
	 * values.
	 * 
	 * @return JSON of student or Error message
	 */
	@PostMapping("/add")
	public String addStudent(@RequestParam("registrationNo") String registrationNo,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

		Student student = new Student(0, registrationNo, firstName, lastName);

		if (studentService.save(student))
			return new JSONObject(student).toString();
		else
			return new JSONObject().put("error", RestValues.SAVED_UNSUCCESS).toString();
	}

	/**
	 * HTTP: http://localhost:8080/appgpa-spring/rest-student/update Parameters
	 * { @RequestBody(Student) }
	 * 
	 * @return JSON of student or Error message
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateStudent(@Validated @RequestBody Student student) {
		if (studentService.update(student))
			return new ResponseEntity<>(student, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JSONObject().put("error", "Student is not Found"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Delete a student by it's id. If deletion is success then it will generate
	 * RESPONSE OK or RESPONSE BAD
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
		if (studentService.deleteStudentById(id)) {
			return new ResponseEntity<>("Student has been deleted successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Deletion has error.", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Get Student by there registration no.
	 */
	@PostMapping(value = "/getStudentByRegistration/{studentId}")
	public Student getStudentByRegistration(@PathVariable("studentId") int studentId) {
		Student student = studentService.getStudentById(studentId);
		return student;
	}
}
