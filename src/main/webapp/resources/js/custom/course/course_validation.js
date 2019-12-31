// validating of Course Code
function isValidCode() {
	let value = document.getElementById("courseCode").value;
	var regName = /^\d{4}$/;
	document.getElementById("course_code_error").innerHTML = "";
	if (!regName.test(value) && value != "") {
		document.getElementById("course_code_error").innerHTML = "Please enter valid Course Code containing 4 digits";
	}
}

// validating of Course Name
function isValidCourseName() {
	let value = document.getElementById("name").value;
	var regName = /^[a-zA-Z]+/;
	document.getElementById("course_name_error").innerHTML = "";
	if (!regName.test(value) && value != "") {
		document.getElementById("course_name_error").innerHTML = "Please enter valid Course Name containing alphabets";
	}
}