package com.ihsinformatics.spring.appgpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihsinformatics.spring.appgpa.model.Semester;
import com.ihsinformatics.spring.appgpa.service.SemesterService;
import com.ihsinformatics.spring.appgpa.values.Values;

@Controller
@RequestMapping("/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	/**
	 * This end point will add semester. Semester No should be unique and not
	 * greater than 8 and less than 1.
	 * 
	 * @param semesterNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addSemester", method = RequestMethod.POST)
	public ModelAndView addSemester(@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(0, semesterNo);

		if (semesterService.save(semester))
			return viewSemesters(Values.CREATED_SUCCESS);
		else
			return viewSemesters(Values.CREATED_UNSUCCESS);
	}

	/**
	 * This end point will update semester. Semester No shouldn't be greater than 8
	 * and lesser than 1 and should be unique.
	 * 
	 * @param semesterId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editSemester", method = RequestMethod.POST)
	public ModelAndView editSemester(@RequestParam("semesterId") int semesterId,
			@RequestParam("semesterNo") int semesterNo) {
		Semester semester = new Semester(semesterId, semesterNo);

		if (semesterService.update(semester))
			return viewSemesters(Values.UPDATED_SUCCESS);
		else
			return viewSemesters(Values.UPDATED_UNSUCCESS);
	}

	/**
	 * It will display the edit form for the semester.
	 * 
	 * @param semesterId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editSemesterForm", method = RequestMethod.GET)
	public ModelAndView editSemesterForm(@RequestParam("id") int semesterId) {
		ModelAndView mav = new ModelAndView("semester_views/edit_semester_form");
		mav.addObject("semester", semesterService.getSemesterById(semesterId));
		return mav;
	}

	/**
	 * This end point will delete a semester by it's semester id.
	 * 
	 * @param semesterId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deleteSemester", method = RequestMethod.GET)
	public ModelAndView deleteSemester(@RequestParam("id") int semesterId) {

		if (semesterService.deleteSemesterById(semesterId))
			return viewSemesters(Values.DELETED_SUCCESS);
		else
			return viewSemesters(Values.DELETED_UNSUCCESS);
	}

	/**
	 * It will show all semesters on web. It will take a parameter of string which
	 * is to identify that if any semester added, updated or deleted, by which we
	 * can generate a notification on front-end.
	 * 
	 * @param alertMessageIdentifier:
	 *            String
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewSemesters")
	public ModelAndView viewSemesters(String alertMessageIdentifier) {
		// TODO Auto-generated method stub
		if (alertMessageIdentifier == null) {
			alertMessageIdentifier = "just-view";
			System.out.println(alertMessageIdentifier);
		}
		ModelAndView mav = new ModelAndView(Values.SEMESTER_VIEW_URL);
		mav.addObject("data", semesterService.getAllSemesters());
		mav.addObject("alertMessageIdentitfier", alertMessageIdentifier);
		return mav;

	}

	/**
	 * This end-point will show the initial view of the module, in this case it will
	 * show add semester view.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initialView() {
		return new ModelAndView("semester_views/add_semester_form");
	}

}