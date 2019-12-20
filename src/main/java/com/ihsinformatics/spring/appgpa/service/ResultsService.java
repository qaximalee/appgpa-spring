package com.ihsinformatics.spring.appgpa.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ihsinformatics.spring.appgpa.model.Result;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public interface ResultsService {

	/**
	 * This function will calculate overall results of a specific student.
	 * 
	 * @param studentId
	 * @return List<Result>
	 */
	List<Result> generateResult(int studentId);

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
	void generate(HttpServletResponse response, JasperPrint jrPrint, String filename, String format)
			throws IOException, JRException;

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
	JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws JRException, IOException;
}
