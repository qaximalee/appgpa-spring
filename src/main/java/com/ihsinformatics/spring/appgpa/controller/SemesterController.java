package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.service.imp.SemesterServiceImp;

@Controller
@RequestMapping("/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	private static final String CREATED_SUCCESS = "from-create";
	private static final String CREATED_UNSUCCESS = "from-create-error";
	private static final String UPDATED_SUCCESS = "from-edit";
	private static final String UPDATED_UNSUCCESS = "from-edit-error";
	private static final String DELETED_SUCCESS = "from-delete";
	private static final String DELETED_UNSUCCESS = "from-delete-error";

	private static final String SEMESTER_VIEW_URL = "/semester_views/view_semesters";

	public void setSemesterService(SemesterServiceImp semesterService) {
		this.semesterService = semesterService;
	}

	@RequestMapping(value = "/addSemester", method = RequestMethod.POST)
	public ModelAndView addSemester(@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(0, semesterNo);

		if (semesterService.save(semester))
			return viewSemesters(CREATED_SUCCESS);
		else
			return viewSemesters(CREATED_UNSUCCESS);
	}

	@RequestMapping(value = "/editSemester", method = RequestMethod.POST)
	public ModelAndView editSemester(@RequestParam("semesterId") int semesterId,
			@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(semesterId, semesterNo);

		if (semesterService.update(semester))
			return viewSemesters(UPDATED_SUCCESS);
		else
			return viewSemesters(UPDATED_UNSUCCESS);
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
			return viewSemesters(DELETED_SUCCESS);
		else
			return viewSemesters(DELETED_UNSUCCESS);
	}

	@RequestMapping(value = "/viewSemesters", method = RequestMethod.GET)
	public ModelAndView viewSemesters(String alertMessageIdentifier) {
		// TODO Auto-generated method stub
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(SEMESTER_VIEW_URL);
		mav.addObject("data", semesterService.getAllSemester());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;
	}

}