package com.ihsinformatics.spring.appgpa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.imp.SemesterServiceImp;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	public void setSemesterService(SemesterServiceImp semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/addSemester", method = RequestMethod.POST)
	public ModelAndView addSemester(@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(0, semesterNo);

		if (semesterService.save(semester))
			return viewSemesters(null, Values.CREATED_SUCCESS);
		else
			return viewSemesters(null, Values.CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editSemester", method = RequestMethod.POST)
	public ModelAndView editSemester(@RequestParam("semesterId") int semesterId,
			@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(semesterId, semesterNo);

		if (semesterService.update(semester))
			return viewSemesters(null, Values.UPDATED_SUCCESS);
		else
			return viewSemesters(null, Values.UPDATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editSemesterForm", method = RequestMethod.GET)
	public ModelAndView editSemesterForm(@RequestParam("id") int semesterId) {
		ModelAndView mav = new ModelAndView("semester_views/edit_semester_form");
		mav.addObject("semester", semesterService.getSemesterById(semesterId));
		return mav;
	}

	@RequestMapping(value = "/deleteSemester", method = RequestMethod.GET)
	public ModelAndView deleteSemester(@RequestParam("id") int semesterId) {

		if (semesterService.deleteSemesterById(semesterId))
			return viewSemesters(null, Values.DELETED_SUCCESS);
		else
			return viewSemesters(null, Values.DELETED_UNSUCCESS);
	}

	@RequestMapping(value = { "/", "/viewSemesters" })
	public ModelAndView viewSemesters(HttpServletRequest request, String alertMessageIdentifier) {
		// TODO Auto-generated method stub
		if (request == null || request.getRequestURL().toString().contains("viewSemesters")) {
			if (alertMessageIdentifier == null) {
				alertMessageIdentifier = "just-view";
				System.out.println(alertMessageIdentifier);
			}
			ModelAndView mav = new ModelAndView(Values.SEMESTER_VIEW_URL);
			mav.addObject("data", semesterService.getAllSemester());
			mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
			return mav;
		} else {
			return new ModelAndView("semester_views/add_semester_form");
		}
	}

}