/*
			This function fetch Courses by semesterId and Fill the dropdown on selecting the Semester
 */

$(document).ready(function() {
	$('#studentId').change(function() {
		//let contextPath = document.getElementById("contextPath").value;
		getStudent();
	});
	$('#semesterId').change(function() {
		getSem();
	});
});
function getSem() {
	var s = document.getElementById("semesterId");
	var semNum = s.options[s.selectedIndex].value;
	let dropdown = $('#courseId');

	dropdown.empty();

	dropdown.append('<option selected="true" disabled>Choose Course</option>');
	dropdown.prop('selectedIndex', 0);
	
	const url = "course-results/getCoursesBySemester?semesterID=" + semNum;

	// Populate dropdown with list of provinces
	$.getJSON(url, function(data) {
		console.log("Data: " + data);
		$.each(data, function(key, entry) {
			dropdown.append($('<option></option>')
					.attr('value', entry.courseId).text(entry.name));
		})
	});
}

/*
	This function fetch Student full name by selecting the Registration No from the dropdown.
 */
function getStudent() {
	var std = document.getElementById("studentId");
	var stdId = std.options[std.selectedIndex].value;
	/* let URL = "";
	if(contextPath.self != window)*/
	const URL = "course-results/getStudentByRegistration?studentID=" + stdId;
	// Populate dropdown with list of provinces
	$.getJSON(URL, function(data) {
		var studentDetails = "Full Name: " + data.firstName + " "
				+ data.lastName;
		$('#studName').text(studentDetails);
		document.getElementById("studName").text = "" + data.firstName + " "
				+ data.lastName;
		console.log(data.firstName + " " + data.lastName);
	});
}