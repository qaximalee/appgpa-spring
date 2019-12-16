package com.ihsinformatics.spring.appgpa.controller.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.model.Student;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;
import com.ihsinformatics.spring.appgpa.service.StudentService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("rest-jasper-report")
public class JasperReports {

	public static final int CREDIT_HOUR = 3;

	@Autowired
	private org.springframework.context.ApplicationContext appContext;
	@Autowired
	private javax.sql.DataSource dataSource;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private CourseResultsService courseResultsService;

	/**
	 * This will create the PDF file for the Top CGPA Holders
	 */
	@GetMapping(value = "/top_cgpa_holder_pdf")
	public ModelAndView getTopCgpaHolderPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:top_cgpa_holders.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		/*
		 * JasperReportsCsvView csvView = new JasperReportsCsvView();
		 * csvView.setJdbcDataSource(dataSource);
		 * csvView.setUrl("classpath:top_cgpa_holders.jrxml");
		 * csvView.setApplicationContext(appContext);
		 */
		/*
		 * JasperReportsHtmlView v = new JasperReportsHtmlView();
		 * v.setJdbcDataSource(dataSource);
		 * v.setUrl("classpath:top_cgpa_holders.jrxml");
		 * v.setApplicationContext(appContext); JasperReportsMultiFormatView mv = new
		 * JasperReportsMultiFormatView(); mv.setJdbcDataSource(dataSource);
		 * mv.setUrl("classpath:top_cgpa_holders.jrxml"); Map<String, Object> mod = new
		 * HashMap<>(); mod.put("format", "x"); mv.setExporterParameters(mod);
		 * mv.setApplicationContext(appContext);
		 */

		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for the Top High Course Marks
	 */
	@GetMapping(value = "/top_high_course_marks_pdf")
	public ModelAndView getTopHighCourseMarksPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:top_high_course_marks.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for the Course Results
	 */
	@GetMapping(value = "/course_results_pdf")
	public ModelAndView getAllCourseResultsPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:course_results.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for the Semester Results
	 */
	@GetMapping(value = "/semester_results_pdf")
	public ModelAndView getAllSemesterResultsPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:semester_results.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for All Students
	 */
	@GetMapping(value = "/all_students_pdf")
	public ModelAndView getAllStudentsPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:all_students.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for All Courses
	 */
	@GetMapping(value = "/all_courses_pdf")
	public ModelAndView getAllCoursesPdf() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:courses.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("value", "value 1");
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This will create the PDF file for All Courses
	 */
	@GetMapping(value = "/single_student_pdf")
	public ModelAndView getSingleStudent() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:single_student.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("queryCondition", 11);
		view.setApplicationContext(appContext);
		return new ModelAndView(view, params);
	}

	/**
	 * This method will generate Overall Result of a Student and it is dynamically
	 * generating the results.
	 */
	@GetMapping(value = "/result_pdf/{studentId}")
	public void getResultOfStudent(@PathVariable("studentId") int studentId, HttpServletResponse response)
			throws JRException, FileNotFoundException {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
		view.setUrl("classpath:results.jasper");

		List<SemesterResults> list = semesterResultsService.getSemesterResultsEntityByStudentId(studentId);
		List<Result> results = new ArrayList<>();

		int counter = 0;
		while (counter < list.size()) {
			List<CourseResults> courseResults = courseResultsService
					.getCourseResultsByStudentAndSemesterId(list.get(counter).getSemester().getSemesterId(), studentId);
			// for (CourseResults courseResult : courseResults) {
			// Result result = new Result(list.get(counter).getSemesterResultId(),
			// courseResult.getCourse().getCourseCode(), courseResult.getCourse().getName(),
			// list.get(counter).getSemester().getSemesterNo(),
			// courseResult.getPercentage(), CREDIT_HOUR,
			// courseResult.getGpa(), courseResult.getGrade(),
			// courseResult.getTotalPoints(),
			// list.get(counter).getSemesterGPA(), list.get(counter).getcGPA());
			// results.add(result);
			// }
			for (int i = 0; i < courseResults.size(); i++) {
				Result result = null;

				// This will show semester and cgpa at last course of a semester.
				if (i + 1 == courseResults.size()) {
					result = new Result(list.get(counter).getSemesterResultId(),
							courseResults.get(i).getCourse().getCourseCode(),
							courseResults.get(i).getCourse().getName(), list.get(counter).getSemester().getSemesterNo(),
							courseResults.get(i).getPercentage(), CREDIT_HOUR, courseResults.get(i).getGpa(),
							courseResults.get(i).getGrade(), courseResults.get(i).getTotalPoints(),
							list.get(counter).getSemesterGPA(), list.get(counter).getcGPA());

				} else {
					result = new Result(list.get(counter).getSemesterResultId(),
							courseResults.get(i).getCourse().getCourseCode(),
							courseResults.get(i).getCourse().getName(), list.get(counter).getSemester().getSemesterNo(),
							courseResults.get(i).getPercentage(), CREDIT_HOUR, courseResults.get(i).getGpa(),
							courseResults.get(i).getGrade(), courseResults.get(i).getTotalPoints(), 0, 0);
				}
				results.add(result);
			}
			counter++;
		}

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(results);

		Student std = studentService.getStudentById(studentId);

		// Sending Parameters to the Field value.
		Map<String, Object> params = new HashMap<>();
		params.put("studentFullName", std.getFirstName() + " " + std.getLastName());
		params.put("studentRegistrationNo", std.getRegistrationNo());

		view.setApplicationContext(appContext);
		JasperPrint jrPrint = JasperFillManager.fillReport(
				"D:\\Java Projects\\appgpa-spring\\src\\main\\resources\\results.jasper", params, beanColDataSource);
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jrPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
	}
}
