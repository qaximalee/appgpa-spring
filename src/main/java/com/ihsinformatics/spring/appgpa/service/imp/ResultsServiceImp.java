package com.ihsinformatics.spring.appgpa.service.imp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.ihsinformatics.spring.appgpa.model.CourseResults;
import com.ihsinformatics.spring.appgpa.model.Result;
import com.ihsinformatics.spring.appgpa.model.SemesterResults;
import com.ihsinformatics.spring.appgpa.service.CourseResultsService;
import com.ihsinformatics.spring.appgpa.service.ResultsService;
import com.ihsinformatics.spring.appgpa.service.SemesterResultsService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ResultsServiceImp implements ResultsService {

	public static final int CREDIT_HOUR = 3;

	@Autowired
	private SemesterResultsService semesterResultsService;

	@Autowired
	private CourseResultsService courseResultsService;

	/**
	 * This function will calculate overall results of a specific student.
	 * 
	 * @param studentId
	 * @return List<Result>
	 */
	@Override
	public List<Result> generateResult(int studentId) {
		List<SemesterResults> list = semesterResultsService.getSemesterResultsEntityByStudentId(studentId);
		if (list.size() <= 0)
			return null;
		List<Result> results = new ArrayList<>();

		int counter = 0;
		while (counter < list.size()) {
			List<CourseResults> courseResults = courseResultsService
					.getCourseResultsByStudentAndSemesterId(list.get(counter).getSemester().getSemesterId(), studentId);
			for (CourseResults courseResult : courseResults) {
				Result result = new Result(list.get(counter).getSemesterResultId(),
						courseResult.getCourse().getCourseCode(), courseResult.getCourse().getName(),
						list.get(counter).getSemester().getSemesterNo(), courseResult.getPercentage(), CREDIT_HOUR,
						courseResult.getGpa(), courseResult.getGrade(), courseResult.getTotalPoints(),
						list.get(counter).getSemesterGPA(), list.get(counter).getcGPA());
				results.add(result);
			}
			counter++;
		}
		return results;
	}

	/**
	 * This method will generate report in a specific format (extension) which is
	 * taken by this function as a String.
	 * 
	 * @param HttpServletResponse
	 * @param JasperPrint
	 * @param filename:
	 *            String
	 * @param format
	 *            (extension i.e pdf, csv or html): String
	 */
	@Override
	public void generate(HttpServletResponse response, JasperPrint jrPrint, String filename, String format)
			throws IOException, JRException {
		// TODO Auto-generated method stub
		if (format.toLowerCase().equals("html"))
			generateHTML(response, jrPrint, filename);
		else if (format.toLowerCase().equals("pdf"))
			generatePDF(response, jrPrint, filename);
		else if (format.toLowerCase().equals("csv"))
			generateCSV(response, jrPrint, filename);
	}

	/**
	 * This method will generate CSV Report.
	 * 
	 * @param HttpServletResponse
	 * @param JasperPrint
	 * @param filename:
	 *            String
	 * 
	 */
	private void generateCSV(HttpServletResponse response, JasperPrint jrPrint, String filename)
			throws IOException, JRException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".csv");
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jrPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		exporter.exportReport();
	}

	/**
	 * This method will generate PDF Report.
	 * 
	 * @param HttpServletResponse
	 * @param JasperPrint
	 * @param filename:
	 *            String
	 * 
	 */
	private void generatePDF(HttpServletResponse response, JasperPrint jrPrint, String filename)
			throws IOException, JRException {
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".pdf");
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jrPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		exporter.exportReport();
	}

	/**
	 * This method will generate HTML Report.
	 * 
	 * @param HttpServletResponse
	 * @param JasperPrint
	 * @param filename:
	 *            String
	 * 
	 */
	private void generateHTML(HttpServletResponse response, JasperPrint jrPrint, String filename)
			throws IOException, JRException {
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".html");
		// TODO Auto-generated method stub
		HtmlExporter exporter = new HtmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jrPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
		exporter.exportReport();
	}

	/**
	 * Create temporary folder to store compiled jasper report which is used of
	 * JasperPrint object.
	 * 
	 * @param fileName:
	 *            String
	 * @param HttpServletRequest
	 * 
	 * @return JasperReport
	 */
	@Override
	public JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException, IOException {

		String tempFolderPath = System.getProperty("java.io.tmpdir") + File.separator + "jasperReport";
		File tempFolder = new File(tempFolderPath);
		if (!tempFolder.exists()) {
			tempFolder.mkdirs();
		}
		String jasperFilePath = tempFolderPath + File.separator + fileName + ".jasper";
		File reportFile = new File(jasperFilePath);
		// If compiled file is not found, then compile XML template
		if (!reportFile.exists()) {
			InputStream jRXmlStream = request.getSession().getServletContext()
					.getResourceAsStream("/WEB-INF/classes/jasper-reports/" + fileName + ".jrxml");
			JasperDesign jasperDesign = JRXmlLoader.load(jRXmlStream);
			JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}
}
