package com.ihsinformatics.spring.appgpa.controller.report;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.ResultsService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("rest-jasper-report")
public class JasperReports {

	public static final int CREDIT_HOUR = 3;

	@Autowired
	private javax.sql.DataSource dataSource;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ResultsService resultsService;

	/**
	 * This will create the PDF file for the Top CGPA Holders
	 * 
	 * @throws JRException
	 * @throws IOException
	 * @throws SQLException
	 */

	@GetMapping(value = "/{format}/top_cgpa_holder")
	public void getTopCgpaHolderReport(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {
		JasperReport jsReport = resultsService.getCompiledFile("top_cgpa_holders", request);

		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("queryCondition", 11);
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "Top CGPA Holders", format);
	}

	/**
	 * This will create the PDF file for the Top High Course Marks
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/top_high_course_marks")
	public void getTopHighCourseMarksReport(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {

		JasperReport jsReport = resultsService.getCompiledFile("top_high_course_marks", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "Top Marks by Course", format);
	}

	/**
	 * This will create the PDF file for the Course Results
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/course_results")
	public void getAllCourseResultsPdf(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {
		JasperReport jsReport = resultsService.getCompiledFile("course_results", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "All Courses Results", format);
	}

	/**
	 * This will create the PDF file for the Semester Results
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/semester_results")
	public void getAllSemesterResultsPdf(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {

		JasperReport jsReport = resultsService.getCompiledFile("semester_results", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "All Semester Results", format);
	}

	/**
	 * This will create the PDF file for All Students
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/all_students")
	public void getAllStudentsPdf(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {

		JasperReport jsReport = resultsService.getCompiledFile("all_students", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "All Students", format);
	}

	/**
	 * This will create the PDF file for All Courses
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/all_courses")
	public void getAllCoursesPdf(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {

		JasperReport jsReport = resultsService.getCompiledFile("courses", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "All Courses", format);
	}

	/**
	 * This will create the PDF file for All Courses
	 * 
	 * @throws IOException
	 * @throws JRException
	 * @throws SQLException
	 */
	@GetMapping(value = "/{format}/single_student")
	public void getSingleStudent(@PathVariable("format") String format, HttpServletResponse response,
			HttpServletRequest request) throws JRException, IOException, SQLException {
		JasperReport jsReport = resultsService.getCompiledFile("single_student", request);
		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("queryCondition", 11);
		JasperPrint jprint = JasperFillManager.fillReport(jsReport, params, dataSource.getConnection());

		resultsService.generate(response, jprint, "Single Student", format);

	}

	/**
	 * This method can generate CSV, PDF and HTML formats for Overall Result of a
	 * Student and it is dynamically generating the results.
	 * 
	 * @throws IOException
	 */
	@GetMapping(value = "/{format}/result/{studentId}")
	public void getResultOfStudent(@PathVariable("format") String format, @PathVariable("studentId") int studentId,
			HttpServletResponse response, HttpServletRequest request) throws JRException, IOException {
		List<Result> results = resultsService.generateResult(studentId);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(results);
		JasperReport jsReport = resultsService.getCompiledFile("results", request);

		Student std = studentService.getStudentById(studentId);

		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("studentFullName", std.getFirstName() + " " + std.getLastName());
		params.put("studentRegistrationNo", std.getRegistrationNo());

		JasperPrint jrPrint = JasperFillManager.fillReport(jsReport, params, beanColDataSource);

		resultsService.generate(response, jrPrint, std.getFirstName() + " " + std.getLastName(), format);
	}
}
