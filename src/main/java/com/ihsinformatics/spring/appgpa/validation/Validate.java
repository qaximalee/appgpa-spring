package com.ihsinformatics.spring.appgpa.validation;

public class Validate {

	/**
	 * Check whether Registration No for the Student is correct of not
	 * 
	 * @return boolean
	 */
	public static boolean isValidRegistrationNo(String registrationNo) {

		String regex = "EP-\\d{7}";

		if (registrationNo.matches(regex))
			return true;
		return false;
	}

	/**
	 * Check whether First Name of the Student is correct of not
	 * 
	 * @return boolean
	 */
	public static boolean isValidFirstName(String firstName) {
		String regex = "[A-Z][a-z]*";

		if (firstName.matches(regex))
			return true;
		return false;
	}

	/**
	 * Check whether Last Name of the Student is correct of not
	 * 
	 * @return boolean
	 */
	public static boolean isValidLastName(String lastName) {

		String regex = "[A-Za-z\\. -]+";

		if (lastName.matches(regex))
			return true;
		return false;
	}

	public static boolean isValidCourseCode(int courseCode) {
		if (courseCode > 999 && courseCode < 10000)
			return true;
		return false;
	}

	public static boolean isValidSemesterNo(int semesterNo) {
		if (semesterNo > 0 && semesterNo < 9)
			return true;
		return false;
	}
}
